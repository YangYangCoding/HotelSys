package edu.unsw.comp9321.jdbc;

public class RoomBean {
		private int roomId;
		private int hotelId;
		private String type;
		private int numBeds;
		private float price;
		private int available;
		
		
		public RoomBean(int roomId, int hotelId, String type, int numBeds,
				float price, int available) {
			super();
			this.roomId = roomId;
			this.hotelId = hotelId;
			this.type = type;
			this.numBeds = numBeds;
			this.price = price;
			this.available = available;
		}


		public int getRoomId() {
			return roomId;
		}


		public void setRoomId(int roomId) {
			this.roomId = roomId;
		}


		public int getHotelId() {
			return hotelId;
		}


		public void setHotelId(int hotelId) {
			this.hotelId = hotelId;
		}


		public String getType() {
			return type;
		}


		public void setType(String type) {
			this.type = type;
		}


		public int getNumBeds() {
			return numBeds;
		}


		public void setNumBeds(int numBeds) {
			this.numBeds = numBeds;
		}


		public float getPrice() {
			return price;
		}


		public void setPrice(float price) {
			this.price = price;
		}


		public int getAvailable() {
			return available;
		}


		public void setAvailable(int available) {
			this.available = available;
		}
		
		
		
		
}
