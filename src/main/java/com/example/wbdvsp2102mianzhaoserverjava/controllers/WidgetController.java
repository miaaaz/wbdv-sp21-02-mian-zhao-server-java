package com.example.wbdvsp2102mianzhaoserverjava.controllers;

import com.example.wbdvsp2102mianzhaoserverjava.models.Widget;
import com.example.wbdvsp2102mianzhaoserverjava.services.WidgetService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class WidgetController {

  @Autowired
  WidgetService service;

  /**
   * Parses Widget JSON object from HTTP body encoded as JSON string. Uses WidgetService to create a
   * new Widget instance and add it to the existing collection of widgets for a topic whose ID is
   * tid.
   *
   * @param tid    The topic id of the new widget
   * @param widget The new widget to create
   * @return the new widget with a unique identifier
   */
  @PostMapping("/api/topics/{tid}/widgets")
  public Widget createWidget(
      @PathVariable("tid") String tid,
      @RequestBody Widget widget) {
    return service.createWidget(tid, widget);
  }

  /**
   * Uses WidgetService to retrieve collection of all widgets and returns a string encoded as a JSON
   * array for a topic whose ID is tid
   *
   * @param tid The topic id
   * @return a collection of all widgets belongs to the specified topic
   */
  @GetMapping("/api/topics/{tid}/widgets")
  public List<Widget> findWidgetsForTopic(@PathVariable("tid") String tid) {
    return service.findWidgetsForTopic(tid);
  }

  /**
   * Parses Widget JSON object from HTTP body encoded as JSON string. Uses WidgetService to find
   * widget instance whose id is equal to wid and update the fields to be the new values in widget
   * parameter.
   *
   * @param wid    The id of the widget to update
   * @param widget The widget to update
   * @return 1 if updates successfully, 0 otherwise
   */
  @PutMapping("/api/widgets/{wid}")
  public int updateWidget(
      @PathVariable("wid") String wid,
      @RequestBody Widget widget) {
    return service.updateWidget(wid, widget);
  }

  /**
   * Uses WidgetService to remove widget whose id is wid.
   *
   * @param wid The id of the widget to delete
   * @return 1 if deletes successfully, 0 otherwise
   */
  @DeleteMapping("/api/widgets/{wid}")
  public int deleteWidget(@PathVariable("wid") String wid) {
    return service.deleteWidget(wid);
  }
}
