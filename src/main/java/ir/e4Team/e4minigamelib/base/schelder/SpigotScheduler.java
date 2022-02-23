package ir.e4Team.e4minigamelib.base.schelder;

import ir.e4Team.e4minigamelib.E4MiniGameLib;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;

public class SpigotScheduler implements Scheduler {
    private final E4MiniGameLib plugin;

    public SpigotScheduler(E4MiniGameLib plugin) {
        this.plugin = plugin;
    }

    public void run(Runnable paramRunnable) {
        Bukkit.getScheduler().runTask(this.plugin, paramRunnable);
    }

    public void runAsync(Runnable paramRunnable) {
        Bukkit.getScheduler().runTaskAsynchronously(this.plugin, paramRunnable);
    }

    public Task runTaskLater(Runnable paramRunnable, long paramLong) {
        BukkitTask bukkitTask = Bukkit.getScheduler().runTaskLater(this.plugin, paramRunnable, paramLong);
        return toGenericTask(bukkitTask);
    }

    public RecurringTask runTaskTimer(Runnable paramRunnable, long paramLong1, long paramLong2) {
        return toRecurringTask(() -> Bukkit.getScheduler().runTaskTimer(this.plugin, paramRunnable, paramLong1, paramLong2));
    }

    public Task runTaskLaterAsync(Runnable paramRunnable, long paramLong) {
        BukkitTask bukkitTask = Bukkit.getScheduler().runTaskLaterAsynchronously(this.plugin, paramRunnable, paramLong);
        return toGenericTask(bukkitTask);
    }

    public RecurringTask runTaskTimerAsync(Runnable paramRunnable, long paramLong1, long paramLong2) {
        return toRecurringTask(() -> Bukkit.getScheduler().runTaskTimerAsynchronously(this.plugin, paramRunnable, paramLong1, paramLong2));
    }

    private Task toGenericTask(final BukkitTask bukkitTask) {
        return new Task() {

            public void cancel() {
                bukkitTask.cancel();
            }
        };
    }

    private RecurringTask toRecurringTask(final TaskCreator creator) {
        return new RecurringTask() {
            private BukkitTask bukkitTask = creator.build();

            public void stop() {
                if (this.bukkitTask != null) {
                    this.bukkitTask.cancel();
                    this.bukkitTask = null;
                }
            }

            public void start() {
                if (this.bukkitTask == null)
                    this.bukkitTask = creator.build();
            }
        };
    }

    interface TaskCreator {
        BukkitTask build();
    }
}
