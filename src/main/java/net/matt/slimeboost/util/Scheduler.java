package net.matt.slimeboost.util;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;

import java.util.ArrayList;
import java.util.List;

public class Scheduler {

  private static final List<Task> tasks = new ArrayList<>();
  private static boolean initialized = false;

  private static class Task {
    int ticks;
    Runnable run;

    Task(int ticks, Runnable run) {
      this.ticks = ticks;
      this.run = run;
    }

  }

  public static void initialize() {
    if (initialized) {
      return;
    }

    ServerTickEvents.END_SERVER_TICK.register(server -> tick());
    initialized = true;
  }

  public static void schedule(int ticks, Runnable run) {
    tasks.add(new Task(Math.max(0, ticks), run));
  }

  private static void tick() {
    for (int i = tasks.size() - 1; i >= 0; i--) {
      Task task = tasks.get(i);
      if (task.ticks <= 0) {
        task.run.run();
        tasks.remove(i);
      } else {
        task.ticks--;
      }
    }
  }
}
