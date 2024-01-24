package baseball;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

public class Application {
	static List<Integer> computerNum = new ArrayList<>();
	static List<Integer> userNum = new ArrayList<>();
	static int ballCount = 0;
	static int strikeCount = 0;
	static boolean finish = false;
	
    public static void main(String[] args) {
        startBaseballGame();
        
    }
    
    public static void startBaseballGame() {
    	System.out.println("숫자 야구 게임을 시작합니다.");
    	
    	computerNum = getComputerNum();
    	userNum = getUserNum();
    	
    	verifyUserNum();
    	
    	getBaseballGameResult();
    }
    
    public static List<Integer> getComputerNum() {
    	while (computerNum.size() < 3) {
    		int randomNumber = Randoms.pickNumberInRange(1, 9);
    		if (!computerNum.contains(randomNumber)) {
    			computerNum.add(randomNumber);
    		}
    	}
    	return computerNum;
    }
    
    public static List<Integer> getUserNum() {
    	System.out.print("숫자를 입력해주세요 : ");
    	String userStr = Console.readLine();
    	
    	return Arrays.stream(userStr.split(""))
    			.mapToInt(Integer::parseInt)
    			.boxed().collect(Collectors.toList());
    }
    
    public static void verifyUserNum() {
    	isLengthThree();
    	isInDuplicatedNum();
    }
    
    public static void isLengthThree() {
    	if (userNum.size() != 3) throw new IllegalArgumentException();
    }
    
    public static void isInDuplicatedNum() {
    	int targetNum = userNum.get(0);
    	for (int i=1; i<userNum.size(); i++) {
    		if (targetNum == userNum.get(i)) throw new IllegalArgumentException();
    		targetNum = userNum.get(i);
    	}
    }
    
    public static void getBaseballGameResult() {
    	ballCount = 0;
    	strikeCount = 0;
    	for (int i=0; i<3; i++) {
    		int targetNum = userNum.get(i);
    		isSameWithComputerNum(targetNum, i);
    	}
    	
    	printBaseballGameResult();
    	
    	if (finish) {
    		System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
    		System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
    		finish = false;
    		whetherRestartGame();
    	} else {
    		userNum = getUserNum();
        	verifyUserNum();
        	
        	getBaseballGameResult();
    	}
    }
    
    public static void isSameWithComputerNum(int targetNum, int idx) {
    	if (computerNum.contains(targetNum) 
    			&& computerNum.indexOf(targetNum) == idx) {
    		strikeCount++;
    	} else if (computerNum.contains(targetNum) 
    			&&computerNum.indexOf(targetNum) != idx ) {
    		ballCount++;
    	} else return;
    }
    
    public static void printBaseballGameResult() {
    	if (ballCount == 0 && strikeCount == 0) {
    		System.out.println("낫싱");
    	} else if (ballCount == 0) {
    		System.out.println(strikeCount + "스트라이크");
    	} else if (strikeCount == 0) {
    		System.out.println(ballCount + "볼");
    	} else {
    		System.out.printf("%d볼 %d스트라이크\n", ballCount, strikeCount);
    	}
    	
    	if (strikeCount == 3) finish = true;
    }
    
    public static void whetherRestartGame() {
    	String restartNum = Console.readLine();
    	
    	if (restartNum.equals("1")) {
    		startBaseballGame();
    	} else if (restartNum.equals("2")) {
    		return;
    	} else throw new IllegalArgumentException();
    }
}
