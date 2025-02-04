package pairmatching.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import camp.nextstep.edu.missionutils.Randoms;
import pairmatching.constant.Course;
import pairmatching.constant.Level;
import pairmatching.constant.Mission;

public class PairMatching {
	private static final String NO_MISSION_ERROR_MESSAGE = "[ERROR] 없는 미션입니다.\n";

	private final HashMap<Course, List<String>> crewNameMap;
	private final List<Matching> matchings;

	public PairMatching() {
		crewNameMap = new HashMap<>();
		matchings = new ArrayList<>();
	}

	public void setCrews(Course course, List<String> crewNames) {
		crewNameMap.put(course, crewNames);
	}

	public List<String> getCrewNames(Course course) {
		return crewNameMap.get(course);
	}

	public void setMatchings() {
		matchings.add(new Matching(Level.LEVEL1, Mission.RACING));
		matchings.add(new Matching(Level.LEVEL1, Mission.LOTTO));
		matchings.add(new Matching(Level.LEVEL1, Mission.BASEBALL));
		matchings.add(new Matching(Level.LEVEL2, Mission.BUCKET));
		matchings.add(new Matching(Level.LEVEL2, Mission.BUY));
		matchings.add(new Matching(Level.LEVEL2, Mission.SUBWAY));
		matchings.add(new Matching(Level.LEVEL4, Mission.OPTIMIZING));
		matchings.add(new Matching(Level.LEVEL4, Mission.DEPLOYMENT));
	}

	public Matching findMatching(String inputMatching) {
		for (Matching matching : getMatchings()) {
			if (matching.isRight(inputMatching)) {
				return matching;
			}
		}
		throw new IllegalArgumentException(NO_MISSION_ERROR_MESSAGE);
	}

	private List<Matching> getMatchings() {
		return matchings;
	}

	public void setMatchingResult(Course course, Matching matching) {
		if (course == Course.BACKEND) {
			matching.setBackResult(shuffle(course));
		}
		if (course == Course.FRONTEND) {
			matching.setFrontResult(crewNameMap.get(course));

		}
	}

	private List<String> shuffle(Course course) {
		return Randoms.shuffle(crewNameMap.get(course));
	}

	public List<List<String>> getMatchingResult(Course course, Matching matching) {
		if (course == Course.BACKEND) {
			return matching.getBackResult();
		}
		if (course == Course.FRONTEND) {
			return matching.getFrontResult();
		}
		return null;
	}

	public void resetAllMatchingResult() {
		for (Matching matching : matchings) {
			matching.resetResults();
		}
	}
}
