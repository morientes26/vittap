package com.innovatrics.commons.vittap.core.studioadmin.mvvm;

import com.innovatrics.commons.vittap.core.studioadmin.service.RoomService;
import com.innovatrics.commons.vittap.model.dao.Room;
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

/**
 * Created by  ??_?¬ morientes on 03/01/2018.
 */
@VariableResolver(DelegatingVariableResolver.class)
public class RoomViewModel {

  private final Logger log = LoggerFactory.getLogger(RoomViewModel.class);

  private String keyword;
  private List<Room> roomList;
  private Room selectedRoom;

  @WireVariable
  private RoomService roomService;

  @Command
  @NotifyChange("roomList")
  public void search(){
    roomList = roomService.findByNameOrDescription(keyword);
  }

  @Command
  @NotifyChange({"roomList", "room"})
  public void save(){
    roomService.save(selectedRoom);
    log.info("Save room {}", selectedRoom);
    //FIXME: component has to refresh by its self without redirect
    Executions.sendRedirect(null);
  }

  @Command
  @NotifyChange(".")
  public void delete(){
    roomService.delete(selectedRoom);
    log.info("Delete room {}", selectedRoom);
    //FIXME: component has to refresh by its self without redirect
    Executions.sendRedirect(null);
  }

  @Command
  @NotifyChange({"roomList", "room"})
  public void clear(){
    init();
  }

  @Init
  public void init(){
    keyword = "";
    roomList = roomService.getAll();
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

  public RoomService getRoomService() {
    return roomService;
  }

  public void setRoomService(RoomService roomService) {
    this.roomService = roomService;
  }

  public String getKeyword() {
    return keyword;
  }

  public void setKeyword(String keyword) {
    this.keyword = keyword;
  }
}
