package com.example.wbdvsp2102mianzhaoserverjava.services;

import com.example.wbdvsp2102mianzhaoserverjava.models.Widget;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class WidgetService {

  private List<Widget> widgets = new ArrayList<>();
  
  /**
   * Creates a new Widget instance and add it to the existing collection of widgets for a topic
   * whose ID is tid.
   *
   * @param tid    The topic id of the new widget
   * @param widget The new widget to add
   * @return a new widget with a unique identifier
   */
  public Widget createWidget(String tid, Widget widget) {
    widget.setTopicId(tid);
    widget.setId((new Date()).getTime());
    widgets.add(widget);
    return widget;
  }

  /**
   * Returns collection of all widgets for a topic whose ID is tid
   *
   * @param tid The topic id
   * @return a collection of all widgets for a topic whose ID is tid
   */
  public List<Widget> findWidgetsForTopic(String tid) {
    List<Widget> ws = new ArrayList<>();
    for (Widget w : widgets) {
      if (w.getTopicId().equals(tid)) {
        ws.add(w);
      }
    }
    return ws;
  }

  /**
   * Updates widget whose id is wid encoded as JSON in HTTP body. Returns 1 if successful, 0
   * otherwise
   *
   * @param wid    The id of the widget to update
   * @param widget The widget to update
   * @return 1 if updates successfully, 0 otherwise
   */
  public int updateWidget(Long wid, Widget widget) {
    for (int i = 0; i < widgets.size(); i++) {
      if (widgets.get(i).getId().equals(wid)) {
        widgets.set(i, widget);
        return 1;
      }
    }
    return 0;
  }

  /**
   * Removes widget whose id is wid. Returns 1 if successful, 0 otherwise
   *
   * @param wid The id of the widget to delete
   * @return 1 if deletes successfully, 0 otherwise
   */
  public int deleteWidget(Long wid) {
    int index = -1;
    for (int i = 0; i < widgets.size(); i++) {
      if (widgets.get(i).getId().equals(wid)) {
        index = i;
        widgets.remove(index);
        return 1;
      }
    }
    return 0;
  }

}
