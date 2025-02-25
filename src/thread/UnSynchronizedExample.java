package thread;

import java.math.BigDecimal;
import java.util.ArrayList;

public class UnSynchronizedExample {
    private static class BankAccount {
        private BigDecimal balance;

        public BigDecimal deposit(BigDecimal amount) {
            this.balance = balance.add(amount);
            return balance;
        }

        public BigDecimal withdraw(BigDecimal amount) {
            this.balance = balance.subtract(amount);
            return balance;
        }

        public BankAccount(BigDecimal balance)  {
            this.balance = balance;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final BankAccount account = new BankAccount(new BigDecimal("0"));
        ArrayList<Thread> pool = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                pool.add(Thread.ofVirtual().start(() -> account.withdraw(new BigDecimal("100"))));
            } else {
                pool.add(Thread.ofVirtual().start(() -> account.deposit(new BigDecimal("100"))));
            }
        }

        while (pool.stream().anyMatch(Thread::isAlive)) {
            System.out.println(" waiting for threads to finish");
            Thread.sleep(1000);
        }

        System.out.println(" here's the balance");
        System.out.println(account.balance);
    }
}
