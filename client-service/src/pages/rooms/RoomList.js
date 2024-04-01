import React from "react";
import Room from "./Room";
import "./RoomList.css";

export default function RoomList({rooms}) {
    if (!rooms) {
        return <div>No rooms available.</div>;
    }
  return (
    <div className="room-list">
      {rooms.map(room => (
        <Room key={room.id} room={room} />
      ))}
    </div>
  );
}