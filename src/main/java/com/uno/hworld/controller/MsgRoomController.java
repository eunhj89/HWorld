package com.uno.hworld.controller;

import com.uno.hworld.common.MsgRoom;
import com.uno.hworld.repository.MsgRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/chat")
public class MsgRoomController {

    private final MsgRoomRepository msgRoomRepository;

    @GetMapping("/room")
    public String rooms() {
        return "/chat/room";
    }

    @GetMapping("/rooms")
    @ResponseBody
    public List<MsgRoom> room() {
        return msgRoomRepository.findAllRoom();
    }

    @PostMapping("/room")
    @ResponseBody
    public MsgRoom createRoom(@RequestParam String name) {
        return msgRoomRepository.createMsgRoom(name);
    }

    @GetMapping("/room/enter/{roomId}")
    public String roomDetail(Model model, @PathVariable String roomId) {
        model.addAttribute("roomId", roomId);
        return "/chat/roomDetail";
    }

    @GetMapping("/room/{roomId}")
    @ResponseBody
    public MsgRoom roomInfo(@PathVariable String roomId) {
        return msgRoomRepository.findRoomById(roomId);
    }
}
