package com.pokemon.test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.pokemon.PokeJoined;
import com.pokemon.exception.PokeJoinedException;

public class PokeJoinedTest {

	@Test
	public void testCorrectInput() throws PokeJoinedException {
		String testInput = "audino bagon baltoy banette bidoof braviary bronzor carracosta charmeleon cresselia croagunk darmanitan deino emboar emolga exeggcute gabite girafarig gulpin haxorus heatmor heatran ivysaur jellicent jumpluff kangaskhan kricketune landorus ledyba loudred lumineon lunatone machamp magnezone mamoswine nosepass petilil pidgeotto pikachu pinsir poliwrath poochyena porygon2 porygonz registeel relicanth remoraid rufflet sableye scolipede scrafty seaking sealeo silcoon simisear snivy snorlax spoink starly tirtouga trapinch treecko tyrogue vigoroth vulpix wailord wartortle whismur wingull yamask";
		PokeJoined.formatInput(testInput);
		List<String> finalList = PokeJoined.join();
		assertEquals(finalList.size(), 23);
		
		testInput = "audino emboar emolga exeggcute gabite";
		PokeJoined.formatInput(testInput);
		finalList = PokeJoined.join();
		assertEquals(finalList.size(), 4);
		assertEquals(finalList.get(0), "gabite");
		assertEquals(finalList.get(1), "exeggcute");
		assertEquals(finalList.get(2), "emolga");
		assertEquals(finalList.get(3), "audino");
		
		testInput = "abc cde fdf sds bca";
		PokeJoined.formatInput(testInput);
		finalList = PokeJoined.join();
		assertEquals(finalList.size(), 3);
		assertEquals(finalList.get(0), "bca");
		assertEquals(finalList.get(1), "abc");
		assertEquals(finalList.get(2), "cde");
	}
	
	@Test(expected=PokeJoinedException.class)
	public void testNullInput() throws PokeJoinedException {
		String testInput = null;
		PokeJoined.formatInput(testInput);
	}
	
	@Test(expected=PokeJoinedException.class)
	public void testEmptyInput() throws PokeJoinedException {
		String testInput = "";
		PokeJoined.formatInput(testInput);
	}
	
	@Test(expected=PokeJoinedException.class)
	public void testSpaceInput() throws PokeJoinedException {
		String testInput = "	";
		PokeJoined.formatInput(testInput);
	}
}
