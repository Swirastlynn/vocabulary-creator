package com.przemyslawlusnia.vocabularycreator.core.utils;

import android.util.Log;
import java.util.Collections;
import java.util.List;

public final class ObjectUtils {

  private ObjectUtils() {
  }

  public static <T> List<T> safeList(List<T> other) {
    return other == null ? Collections.<T>emptyList() : other;
  }

  // todo null policy
  public static <T> boolean isNotNull(String tag, String function, T other) {
    boolean result = other != null;
    if (!result) {
      Log.e(tag, "null in " + function);
    }
    return result;
  }
}
