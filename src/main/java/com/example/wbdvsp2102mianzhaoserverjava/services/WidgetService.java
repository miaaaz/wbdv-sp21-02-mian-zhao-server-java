package com.example.wbdvsp2102mianzhaoserverjava.services;

import com.example.wbdvsp2102mianzhaoserverjava.models.Widget;
import com.example.wbdvsp2102mianzhaoserverjava.repositories.WidgetRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WidgetService {

  @Autowired
  WidgetRepository repository;

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
    return repository.save(widget);
  }

  /**
   * Returns collection of all widgets
   * @return collection of all widgets
   */
  public List<Widget> findAllWidgets() {
    return (List<Widget>) repository.findAll();
  }

  /**
   * Returns collection of all widgets for a topic whose ID is tid
   *
   * @param tid The topic id
   * @return a collection of all widgets for a topic whose ID is tid
   */
  public List<Widget> findWidgetsForTopic(String tid) {
    return repository.findWidgetsForTopic(tid);
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
    Widget originalWidget = repository.findWidgetById(wid);

    originalWidget.setText(widget.getText());
    originalWidget.setUrl(widget.getUrl());
    originalWidget.setHeight(widget.getHeight());
    originalWidget.setWidth(widget.getWidth());
    originalWidget.setOrdered(widget.getOrdered());
    originalWidget.setSize(widget.getSize());
    originalWidget.setType(widget.getType());

    repository.save(originalWidget);
    return 1;
  }

  /**
   * Removes widget whose id is wid. Returns 1 if successful, 0 otherwise
   *
   * @param wid The id of the widget to delete
   * @return 1 if deletes successfully, 0 otherwise
   */
  public int deleteWidget(Long wid) {
    repository.deleteById(wid);
    return 1;
  }

  /**
   * Returns a single widget instance whose id is equal to wid
   * @param wid The widget id
   * @return a single widget instance whose id is equal to wid
   */
  public Widget findWidgetById(Long wid) {
    return repository.findWidgetById(wid);
  }

}
