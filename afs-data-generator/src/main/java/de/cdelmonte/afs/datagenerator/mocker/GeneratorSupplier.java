package de.cdelmonte.afs.datagenerator.mocker;

import java.util.List;
import de.cdelmonte.afs.datagenerator.model.Mock;

@FunctionalInterface
interface GeneratorSupplier<T> {
  List<? extends Mock> supplyMocks(int howMany);
}
