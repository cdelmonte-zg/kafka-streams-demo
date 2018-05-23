package de.cdelmonte.afs.datagenerator.mocker;

import java.util.List;
import de.cdelmonte.afs.datagenerator.model.Mock;

@FunctionalInterface
public interface GeneratorSupplier<T> {
  public List<? extends Mock> supplyMocks(int howMany);
}
