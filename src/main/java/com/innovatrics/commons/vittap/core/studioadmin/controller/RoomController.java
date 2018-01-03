package com.innovatrics.commons.vittap.core.studioadmin.controller;

import com.innovatrics.commons.vittap.core.studioadmin.service.RoomService;
import com.innovatrics.commons.vittap.model.dao.Room;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.*;

import java.io.Serializable;
import java.util.List;


@VariableResolver(DelegatingVariableResolver.class)
public class RoomController extends SelectorComposer<Component> implements Serializable{

  private final Logger log = LoggerFactory.getLogger(RoomController.class);

  private static final long serialVersionUID = 1L;

  private Grid roomList;

  @Wire
  private Footer footerRoom;

  @Wire
  private Label updateId;
  @Wire
  private Textbox updateName;
  @Wire
  private Textbox updateDescription;

  @WireVariable
  RoomService roomService;

  private Room room;
  private List<Room> rooms;

  public RoomController(){}


  @Listen(value="onClick = .row")
  public void choose(Event event){
    Long id = Long.valueOf(event.getTarget().getId());
    room = roomService.findOne(id);
    updateId.setValue(String.valueOf(room.getId()));
    updateName.setValue(String.valueOf(room.getName()));
    updateDescription.setValue(String.valueOf(room.getDescription()));
    log.info("Select room {}", room);
  }

  @Listen(value = "onClick = #btnSave")
  public void save(){
    if (room==null){
      room = new Room();
    }
    room.setName(updateName.getValue());
    room.setDescription(updateDescription.getValue());
    room = roomService.save(room);
    log.info("Save room {}", room);
  }

  @Listen(value = "onClick = #btnDelete")
  public void delete(){
    if (room==null) return;
    roomService.delete(room);
    log.info("Delete room {}", room);
  }

  public void doAfterCompose(Component comp) throws Exception {
    super.doAfterCompose(comp);
    roomList = (Grid) comp;
    ListModel<Room> model = new ListModelList<>(roomService.getAll());
    roomList.setModel(model);
    footerRoom.setLabel("A Total of " + model.getSize() + " Room Items");
  }

}