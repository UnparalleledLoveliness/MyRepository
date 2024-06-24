package com.tj.demo12;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;

public class generator extends AnAction {
  @Override
  public void actionPerformed(AnActionEvent event) {
    new ShowChose(event);
  }
}
