// package tian.pusen.java8.lock.stamplock;
//
//import java.util.List;
//import java.util.stream.*;
//import java.util.concurrent.*;
//import java.util.concurrent.locks.*;
//
//public class StampedLockSample {
//	private final StampedLock lock = new StampedLock();
//    private double balance;
//    
//    public void deposit(double amount) {
//        long stamp = lock.writeLock();
//        try {
//            balance = balance + amount;
//        } finally {
//            lock.unlockWrite(stamp);
//        }
//    }
//
//    public double getBalance() {
//        long stamp = lock.readLock();
//        try {
//            return balance;
//        } finally {
//            lock.unlockRead(stamp);
//        }
//    }
//    public static void main(String[] args) throws Exception {
//    	StampedLockSample account = new StampedLockSample();
//    	ExecutorService pool = Executors.newCachedThreadPool();
//        List<Callable<Double>> callables = IntStream.range(1,5)
//                .mapToObj(x -> (Callable<Double>) () -> {
////                    if (x % 2 == 0) {
////                        return account.getBalance();
////                    } else {
////                        account.deposit(x);
////                        return 0d;
////                    }
//                    account.deposit(x);
//                    return 0d;
//                })
//                .collect(Collectors.toList());
//        pool.invokeAll(callables).forEach(x -> {
//            try {
//                System.out.println(x.get());
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } catch (ExecutionException e) {
//                e.printStackTrace();
//            }
//        });
//        pool.shutdown();
//        System.out.println(account.getBalance());
//    }
//}
