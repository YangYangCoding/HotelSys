package edu.unsw.comp9321.jdbc;

import java.util.Date;

public class PeakDTO {
	private Date peakstart;
	private Date peakend;
	
	public PeakDTO(Date peakstart, Date peakend) {
		super();
		this.peakstart = peakstart;
		this.peakend = peakend;
	}

	public Date getPeakstart() {
		return peakstart;
	}

	public void setPeakstart(Date peakstart) {
		this.peakstart = peakstart;
	}

	public Date getPeakend() {
		return peakend;
	}

	public void setPeakend(Date peakend) {
		this.peakend = peakend;
	}
	
	
}
