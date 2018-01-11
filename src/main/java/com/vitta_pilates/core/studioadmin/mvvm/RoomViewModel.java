package com.vitta_pilates.core.studioadmin.mvvm;

import com.vitta_pilates.core.studioadmin.service.RoomService;
import com.vitta_pilates.model.dao.Room;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;

import java.util.List;


@VariableResolver(DelegatingVariableResolver.class)
public class RoomViewModel {

  private final Logger log = LoggerFactory.getLogger(RoomViewModel.class);

  private String keyword;
  private List<Room> roomList;
  private Room selectedRoom;

  @WireVariable("roomService")
  private RoomService service;

  @Command
  @NotifyChange("roomList")
  public void search(){
    roomList = service.findByNameOrDescription(keyword);
  }

  @Command
  @NotifyChange({"roomList", "selectedRoom"})
  public void submit(){
    service.save(selectedRoom);
    log.info("Save room {}", selectedRoom);
    //FIXME: component has to refresh by its self without redirect
    Executions.sendRedirect(null);
  }

  @Command
  @NotifyChange(".")
  public void delete(){
    service.delete(selectedRoom);
    log.info("Delete room {}", selectedRoom);
    //FIXME: component has to refresh by its self without redirect
    Executions.sendRedirect(null);
  }

  @Command
  @NotifyChange({"roomList", "selectedRoom"})
  public void clear(){
    init();
  }

  @Init
  public void init(){
    keyword = "";
    roomList = service.getAll();
    selectedRoom = new Room();
  }

  public List<Room> getRoomList() {
    return roomList;
  }

  public void setRoomList(List<Room> roomList) {
    this.roomList = roomList;
  }

  public Room getSelectedRoom() {
    return selectedRoom;
  }

  public void setSelectedRoom(Room selectedRoom) {
    this.selectedRoom = selectedRoom;
  }

  public RoomService getService() {
    return service;
  }

  public void setService(RoomService service) {
    this.service = service;
  }

  public String getKeyword() {
    return keyword;
  }

  public void setKeyword(String keyword) {
    this.keyword = keyword;
  }
}
