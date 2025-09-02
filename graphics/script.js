class Graphic {
	static Init() {
		this.addLoad();

		this.svg = document.body.querySelector("svg");

		this.values = [
			0,
			1_000,
			10_000,
			50_000,
			100_000,
			200_000,
			300_000,
			400_000,
			500_000
		];

		this.generateX();

		var reports = Data.getReports();

		var maxTime = "";

		for (let i = 0; i < reports.length; i++) {
			if (this.values.includes(reports[i].amount)) {
				console.log(reports[i]);

				// let line = document.createElementNS("http://www.w3.org/2000/svg", "line");

				// line.setAttribute("x1", 10 + "%");
				// line.setAttribute("y1", 80 + "%");

				// line.setAttribute("x2", 90 + "%");
				// line.setAttribute("y2", 4 + "%");

				// this.svg.append(line);

				break;
			}
		}
	}

	static generateX() {
		for (let i = 0; i < this.values.length; i++) {
			let current = this.formatNumber(this.values[i]);

			console.log(current);

			let text = document.createElementNS("http://www.w3.org/2000/svg", "text");

			text.textContent = current;

			text.setAttribute("x", ((100 - 10 - 10) / (this.values.length - 1) * i + 10) + "%");
			text.setAttribute("y", "88%");

			this.svg.append(text);
		}
	}

	static generateY() {
		for (let i = 0; i < this.values.length; i++) {

		}
	}

	static formatNumber(number) {
		if (number >= 1000_000_000) return number / 1000_000_000 + "b";
		if (number >= 1000_000) return number / 1000_000 + "m";
		if (number >= 1000) return number / 1000 + "k";

		return Math.floor(number);
	}

	static formatNumber2(number) {
		number = Math.floor(number).toString();

		var result = "";

		while (number.length > 3) {
			result = "." + number.slice(number.length - 3) + result;

			number = number.slice(0, number.length - 3);
		}

		result = number + result;

		return result;
	}

	static addLoad() {
		window.addEventListener("load", function() {
			setTimeout(function() {
				window.location.reload();
			}, 10000);
		});
	}
}

Graphic.Init();