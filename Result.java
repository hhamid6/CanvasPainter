package javaSwing;

import java.awt.Rectangle;

public class Result {
	private String name;
	private double score;
	private Rectangle boundingBox;
	private Unistroke candidate, matchedTemplate;
	
	public Result(String name, double score) {
		this.name = name;
		this.score = score;
	}
	
	public Result(Unistroke matchedTemplate, Unistroke candidate, double score) {
		this.matchedTemplate = matchedTemplate;
		this.candidate = candidate;
		this.score = score;
	}
	
	
	public String toString() {
		return this.name;
	}
	
	public String getName() {
		return this.matchedTemplate.getName();
	}
	public double getScore() {
		return this.score;
	}
	public Rectangle getBoundingBox() {
		return candidate.getOriginalBBox();
	}
	public Unistroke getMatchedTemplate() {
		return matchedTemplate;
	}
	public Unistroke getCandidate() {
		return candidate;
	}
}
