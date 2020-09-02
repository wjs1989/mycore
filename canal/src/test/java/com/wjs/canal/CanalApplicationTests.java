package com.wjs.canal;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;
import com.alibaba.otter.canal.client.impl.SimpleCanalConnector;
import com.alibaba.otter.canal.protocol.CanalEntry;
import com.alibaba.otter.canal.protocol.Message;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.InetSocketAddress;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class CanalApplicationTests {

	@Test
	void contextLoads() {


	}

	public static void main1(String args[]) {
		String destination = "example";
		String ip = "10.204.125.109";
		int batchSize = 1024;
		int count = 0;
		int sum = 0;
		int perSum = 0;
		long start = System.currentTimeMillis();
		long end = 0;
		final ArrayBlockingQueue<Long> queue = new ArrayBlockingQueue<Long>(100);
		try {
			final CanalConnector connector = CanalConnectors.newSingleConnector(new InetSocketAddress(ip, 11111),
					destination,
					"",
					"");

			Thread ackThread = new Thread(new Runnable() {

				@Override
				public void run() {
					while (true) {
						try {
							long batchId = queue.take();
							connector.ack(batchId);
						} catch (InterruptedException e) {
						}
					}
				}
			});
			ackThread.start();

			((SimpleCanalConnector) connector).setLazyParseEntry(true);
			connector.connect();
			connector.subscribe();
			while (true) {
				Message message = connector.getWithoutAck(batchSize, 100L, TimeUnit.MILLISECONDS);
				long batchId = message.getId();
				int size = message.getRawEntries().size();
				sum += size;
				perSum += size;
				count++;
				queue.add(batchId);
				if (count % 10 == 0) {
					end = System.currentTimeMillis();
					if (end - start != 0) {
						long tps = (perSum * 1000) / (end - start);
						System.out.println(" total : " + sum + " , current : " + perSum + " , cost : " + (end - start)
								+ " , tps : " + tps);
						start = end;
						perSum = 0;
					}
				}

				List<CanalEntry.Entry> entries = message.getEntries();
				if(entries!= null && !entries.isEmpty()){
					for(CanalEntry.Entry e : entries){
						e.getAllFields();
					}
				}
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {
		// 创建链接
		CanalConnector connector = CanalConnectors.newSingleConnector(new InetSocketAddress("10.204.125.109",
				11111), "example", "", "");
		int batchSize = 1000;
		int emptyCount = 0;
		try {
			connector.connect();
			connector.subscribe(".*\\..*");
			connector.rollback();
			int totalEmptyCount = 120000;
			while (emptyCount < totalEmptyCount) {
				Message message = connector.getWithoutAck(batchSize); // 获取指定数量的数据
				long batchId = message.getId();
				int size = message.getEntries().size();
				if (batchId == -1 || size == 0) {
					emptyCount++;
					//System.out.println("empty count : " + emptyCount);
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
					}
				} else {
					emptyCount = 0;
					printEntry(message.getEntries());
				}

				connector.ack(batchId); // 提交确认
			}

			System.out.println("empty too many times, exit");
		} finally {
			connector.disconnect();
		}
	}

	private static void printEntry(List<CanalEntry.Entry> entrys) {
		for (CanalEntry.Entry entry : entrys) {
			if (entry.getEntryType() == CanalEntry.EntryType.TRANSACTIONBEGIN || entry.getEntryType() == CanalEntry.EntryType.TRANSACTIONEND) {
				continue;
			}

			CanalEntry.RowChange rowChage = null;
			try {
				rowChage = CanalEntry.RowChange.parseFrom(entry.getStoreValue());
			} catch (Exception e) {
				throw new RuntimeException("ERROR ## parser of eromanga-event has an error , data:" + entry.toString(),
						e);
			}

			CanalEntry.EventType eventType = rowChage.getEventType();
			System.out.println(String.format("================&gt; binlog[%s:%s] , name[%s,%s] , eventType : %s",
					entry.getHeader().getLogfileName(), entry.getHeader().getLogfileOffset(),
					entry.getHeader().getSchemaName(), entry.getHeader().getTableName(),
					eventType));

			for (CanalEntry.RowData rowData : rowChage.getRowDatasList()) {
				if (eventType == CanalEntry.EventType.DELETE) {
					printColumn(rowData.getBeforeColumnsList());
				} else if (eventType == CanalEntry.EventType.INSERT) {
					printColumn(rowData.getAfterColumnsList());
				} else {
					System.out.println("-------&gt; before");
					printColumn(rowData.getBeforeColumnsList());
					System.out.println("-------&gt; after");
					printColumn(rowData.getAfterColumnsList());
				}
			}
		}
	}

	private static void printColumn(List<CanalEntry.Column> columns) {
		for (CanalEntry.Column column : columns) {
			System.out.println(column.getName() + " : " + column.getValue() + "    update=" + column.getUpdated());
		}
	}
}
