package com.folksdev.springshell.util;

import java.util.List;
import java.util.stream.Collectors;

import com.folksdev.springshell.dto.PharmacyItem;
import org.springframework.shell.table.ArrayTableModel;
import org.springframework.shell.table.BorderStyle;
import org.springframework.shell.table.TableBuilder;
import org.springframework.stereotype.Component;

@Component
public class PharmacyFormatter {

  private static String[] toRow(PharmacyItem item) {
    return new String[] {item.name(), item.dist(), item.address(), item.phone()};
  }

  public String convertToTable(List<PharmacyItem> items) {
    var data = items.stream().map(PharmacyFormatter::toRow).collect(Collectors.toList());

    data.add(0, new String[] {"name", "dist", "address", "phone"});
    ArrayTableModel tableModel = new ArrayTableModel(data.toArray(Object[][]::new));
    TableBuilder tableBuilder = new TableBuilder(tableModel);

    tableBuilder.addHeaderAndVerticalsBorders(BorderStyle.fancy_light);
    return tableBuilder.build().render(100);
  }
}
