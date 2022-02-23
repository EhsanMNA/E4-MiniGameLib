package ir.e4Team.e4minigamelib.base.schelder;

public interface Scheduler {
    void run(Runnable paramRunnable);

    void runAsync(Runnable paramRunnable);

    Task runTaskLater(Runnable paramRunnable, long paramLong);

    RecurringTask runTaskTimer(Runnable paramRunnable, long paramLong1, long paramLong2);

    Task runTaskLaterAsync(Runnable paramRunnable, long paramLong);

    RecurringTask runTaskTimerAsync(Runnable paramRunnable, long paramLong1, long paramLong2);
}
