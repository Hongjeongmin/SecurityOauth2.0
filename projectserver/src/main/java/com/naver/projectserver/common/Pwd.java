package com.naver.projectserver.common;

import org.springframework.stereotype.Component;

@Component
public class Pwd {
	public String getRnadomcode() {
		StringBuilder sb = new StringBuilder();

		for (int seq = 0; seq < 6; seq++) {
			// 0~3의 값
			int pick = (int) (Math.random() * 3);

			switch (pick) {
			case 0:
				// 0~9 숫자
				sb.append((int) (Math.random() * 10));
				break;
			case 1:
				// a~z
				sb.append((char) ((int) (Math.random() * 26 + 65)));
				break;
			case 2:
				// A~z
				sb.append((char) ((int) (Math.random() * 26 + 97)));
				break;

			}

		}
		return sb.toString();
	}
	public String getRnadomcode(int n) {
		StringBuilder sb = new StringBuilder();

		for (int seq = 0; seq < n; seq++) {
			// 0~3의 값
			int pick = (int) (Math.random() * 3);

			switch (pick) {
			case 0:
				// 0~9 숫자
				sb.append((int) (Math.random() * 10));
				break;
			case 1:
				// a~z
				sb.append((char) ((int) (Math.random() * 26 + 65)));
				break;
			case 2:
				// A~z
				sb.append((char) ((int) (Math.random() * 26 + 97)));
				break;

			}

		}
		return sb.toString();
	}
}
