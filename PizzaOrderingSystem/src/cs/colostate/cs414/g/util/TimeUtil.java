package cs.colostate.cs414.g.util;

import java.util.ArrayList;
import java.util.Vector;


public class TimeUtil implements Update,java.io.Serializable
{
	private static double timeScale = 1.0;
	private double start, end;
	private ArrayList< Update > updateableObjs = new ArrayList< Update >();
	public TimeUtil() {
		
	}
	public TimeUtil(double start, double end) {
		this.start = start;
		this.end = end;
	}
	public double getDuration() {
		return end - start;
	}

	/**
	 * Get the start time.
	 * @return the start
	 */
	public double getStart() {
		return start;
	}

	/**
	 * Set the start time.
	 * @param start - the start to set
	 */
	public void setStart(double start) {
		this.start = start;
	}

	/**
	 * Get the end time.
	 * @return the end
	 */
	public double getEnd() {
		return end;
	}

	/**
	 * Set the end time.
	 * @param end - the end to set
	 */
	public void setEnd(double end) {
		this.end = end;
	}
	
	public static double getCurrentTime() {
		return ((double)System.nanoTime()) * getTimeScale() * 1.0e-9;
	}
	
	public static double getTimeScale() {
		return timeScale;
	}
	public static boolean hasTimeElapsed(double startTime, double desiredElapsedTime) {
		return (getCurrentTime() - startTime) >= desiredElapsedTime;
	}
	public void update() {
		for(Update o : updateableObjs) {
			o.update();
		}
	}
	public void addUpdateable(Update obj) {
		if (updateableObjs.contains(obj) == false) {
			updateableObjs.add(obj);
		}
	}
	public static void setTimeScale(double newTimeScale) {
		timeScale = newTimeScale;
	}
}