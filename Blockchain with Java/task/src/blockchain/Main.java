package blockchain;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args) {
        var minerCount = 5;
        var runDurationSeconds = 1;
        var executor = Executors.newFixedThreadPool(minerCount);
        var futures = new ArrayList<Future<Block>>();

        // Запускаем майнеров
        for (int i = 0; i < minerCount; i++) {
            futures.add(executor.submit(new Miner(String.valueOf(i))));
        }

        //System.out.println("⛏️  Mining started with " + minerCount + " miners. Running for " + runDurationSeconds + " seconds...");

        try {
            Thread.sleep(runDurationSeconds * 1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            //System.out.println("\n🛑 Shutting down miners...");
            executor.shutdownNow(); // Прерываем все задачи

//             Опционально: обработать результаты тех, кто успел добыть блок
//            for (Future<Block> future : futures) {
//                try {
//                    Block block = future.get(1, TimeUnit.SECONDS); // небольшой таймаут
//                    if (block != null) {
//                        System.out.println("🏆 Final block by miner #" + block.getCreatedBy() + " (ID: " + block.getId() + ")");
//                    }
//                } catch (Exception ignored) {
//                    // Игнорируем — большинство фьючеров не завершатся
//                }
//            }

            //System.out.println("✅ Mining finished. Total blocks: " + (blockchain.getLastBlock().getId() + 1));
        }
    }
}