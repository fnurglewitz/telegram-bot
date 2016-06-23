package it.pwned.telegram.samplebot.trivia.type;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum QuestionCategory {

	//@formatter:off
	
  ANY("any","Any Category"),
  GENERAL("9","General Knowledge"),
  BOOKS("10","Entertainment: Books"),
  FILM("11","Entertainment: Film"),
  MUSIC("12","Entertainment: Music"),
  THEATRE("13","Entertainment: Musicals & Theatres"),
  TV("14","Entertainment: Television"),
  VIDEOGAMES("15","Entertainment: Video Games"),
  BOARDGAMES("16","Entertainment: Board Games"),
  SCIENCE("17","Science & Nature"),
  COMPUTERS("18","Science: Computers"),
  MATH("19","Science: Mathematics"),
  MYTHOLOGY("20","Mythology"),
  SPORTS("21","Sports"),
  GEOGRAPHY("22","Geography"),
  HISTORY("23","History"),
  POLITICS("24","Politics"),
  ART("25","Art"),
  CELEBRITIES("26","Celebrities"),
  ANIMALS("27","Animals"),
  CARS("28","Cars"),
  COMICS("29","Entertainment: Comic Books"),
  GADGETS("30","Science: Gadgets");	

	//@formatter:on

	private final String code;
	private final String description;

	private QuestionCategory(String code, String description) {
		this.code = code;
		this.description = description;
	}

	@JsonValue
	@Override
	public String toString() {
		return description;
	}

	public static QuestionCategory fromCode(String code) {

		switch (code) {
		case "any":
			return QuestionCategory.ANY;
		case "9":
			return QuestionCategory.GENERAL;
		case "10":
			return QuestionCategory.BOOKS;
		case "11":
			return QuestionCategory.FILM;
		case "12":
			return QuestionCategory.MUSIC;
		case "13":
			return QuestionCategory.THEATRE;
		case "14":
			return QuestionCategory.TV;
		case "15":
			return QuestionCategory.VIDEOGAMES;
		case "16":
			return QuestionCategory.BOARDGAMES;
		case "17":
			return QuestionCategory.SCIENCE;
		case "18":
			return QuestionCategory.COMPUTERS;
		case "19":
			return QuestionCategory.MATH;
		case "20":
			return QuestionCategory.MYTHOLOGY;
		case "21":
			return QuestionCategory.SPORTS;
		case "22":
			return QuestionCategory.GEOGRAPHY;
		case "23":
			return QuestionCategory.HISTORY;
		case "24":
			return QuestionCategory.POLITICS;
		case "25":
			return QuestionCategory.ART;
		case "26":
			return QuestionCategory.CELEBRITIES;
		case "27":
			return QuestionCategory.ANIMALS;
		case "28":
			return QuestionCategory.CARS;
		case "29":
			return QuestionCategory.COMICS;
		case "30":
			return QuestionCategory.GADGETS;
		default:
			return QuestionCategory.ANY;
		}

	}

	@JsonCreator
	public static QuestionCategory fromDescription(String description) {

		switch (description) {
		case "Any Category":
			return QuestionCategory.ANY;
		case "General Knowledge":
			return QuestionCategory.GENERAL;
		case "Entertainment: Books":
			return QuestionCategory.BOOKS;
		case "Entertainment: Film":
			return QuestionCategory.FILM;
		case "Entertainment: Music":
			return QuestionCategory.MUSIC;
		case "Entertainment: Musicals &amp; Theatres":
			return QuestionCategory.THEATRE;
		case "Entertainment: Television":
			return QuestionCategory.TV;
		case "Entertainment: Video Games":
			return QuestionCategory.VIDEOGAMES;
		case "Entertainment: Board Games":
			return QuestionCategory.BOARDGAMES;
		case "Science &amp; Nature":
			return QuestionCategory.SCIENCE;
		case "Science: Computers":
			return QuestionCategory.COMPUTERS;
		case "Science: Mathematics":
			return QuestionCategory.MATH;
		case "Mythology":
			return QuestionCategory.MYTHOLOGY;
		case "Sports":
			return QuestionCategory.SPORTS;
		case "Geography":
			return QuestionCategory.GEOGRAPHY;
		case "History":
			return QuestionCategory.HISTORY;
		case "Politics":
			return QuestionCategory.POLITICS;
		case "Art":
			return QuestionCategory.ART;
		case "Celebrities":
			return QuestionCategory.CELEBRITIES;
		case "Animals":
			return QuestionCategory.ANIMALS;
		case "Cars":
			return QuestionCategory.CARS;
		case "Entertainment: Comic Books":
			return QuestionCategory.COMICS;
		case "Science: Gadgets":
			return QuestionCategory.GADGETS;
		default:
			return QuestionCategory.ANY;
		}

	}

	public String getCode() {
		return code;
	}

}
