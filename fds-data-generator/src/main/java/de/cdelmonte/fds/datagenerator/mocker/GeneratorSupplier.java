package de.cdelmonte.fds.datagenerator.mocker;

import java.util.List;
import de.cdelmonte.fds.datagenerator.model.Mock;

@FunctionalInterface
interface GeneratorSupplier<T> {
  List<? extends Mock> supplyMocks(int howMany);
}
