package com.vitta_pilates.model.comparator;


import com.vitta_pilates.model.dao.ProgramTemplate;

import java.io.Serializable;
import java.util.Comparator;

public class ProgramTemplateComparator implements Comparator<ProgramTemplate>, Serializable {

  private static final long serialVersionUID = -2127053833562854322L;

  private boolean asc = true;
  private int type = 0;

  public ProgramTemplateComparator(boolean asc, int type) {
    this.asc = asc;
    this.type = type;
  }

  public int getType() {
    return type;
  }

  public void setType(int type) {
    this.type = type;
  }

  @Override
  public int compare(ProgramTemplate p1, ProgramTemplate p2) {
    switch (type) {
      case 1: // Compare by name
        return p1.getName().compareTo(p2.getName()) * (asc ? 1 : -1);
      case 2: // Compare by description
        return p1.getDescription().compareTo(p2.getDescription()) * (asc ? 1 : -1);
      default: // by ID
        return p1.getId().compareTo(p2.getId()) * (asc ? 1 : -1);
    }

  }
}
