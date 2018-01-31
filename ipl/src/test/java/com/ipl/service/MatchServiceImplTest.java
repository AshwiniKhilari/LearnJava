package com.ipl.service;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

import com.ipl.dao.MatchDao;
import org.joda.time.LocalDate;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.ipl.model.Match;


public class MatchServiceImplTest {

	@Mock
    MatchDao dao;
	
	@InjectMocks
	MatchServiceImpl matchService;
	
	@Spy
	List<Match> matches = new ArrayList<Match>();
	
	@BeforeClass
	public void setUp(){
		MockitoAnnotations.initMocks(this);
        matches = getMatchList();
	}

	@Test
	public void findById(){
		Match match = matches.get(0);
		when(dao.findMatchById(anyInt())).thenReturn(match);
		Assert.assertEquals(matchService.findMatchById(match.getId()),match);
	}

	@Test
	public void saveMatch(){
		doNothing().when(dao).saveMatch(any(Match.class));
        matchService.saveMatch(any(Match.class));
		verify(dao, atLeastOnce()).saveMatch(any(Match.class));
	}
	
	@Test
	public void updateMatch(){
        Match match = matches.get(0);
		when(dao.findMatchById(anyInt())).thenReturn(match);
		matchService.updateMatch(match);
		verify(dao, atLeastOnce()).findMatchById(anyInt());
	}

	@Test
	public void removeMatchById(){
		doNothing().when(dao).removeMatchById(anyInt());
        matchService.removeMatchById(anyInt());
		verify(dao, atLeastOnce()).removeMatchById(anyInt());
	}
	
	@Test
	public void findAllMatches(){
		when(dao.findAllMatches()).thenReturn(matches);
		Assert.assertEquals(matchService.findAllMatches(), matches);
	}
	


	
	
	public List<Match> getMatchList(){
        List<Match> matches = new ArrayList<Match>();
        Match match1 = new Match();
        //match.setId(6);
        match1.setMatchDate("06-04-2017");
        match1.setMatchDay("Thursday");
        match1.setMatchDetails("Rising Pune Supergiants vs Mumbai Indians");
        match1.setMatchStatus("A");
        match1.setMatchTime("20:00 PM (2:30pm GMT)");
        match1.setMatchVenue("Pune");

        Match match2 = new Match();
        //match.setId(6);
        match2.setMatchDate("05-04-2017");
        match2.setMatchDay("Wednesday");
        match2.setMatchDetails("Sunrisers Hyderabad vs Royal Challengers Bangalore");
        match2.setMatchStatus("A");
        match2.setMatchTime("20:00 PM (2:30pm GMT)");
        match2.setMatchVenue("Hyderabad");;

        matches.add(match1);
        matches.add(match1);
		return matches;
	}
	
}
