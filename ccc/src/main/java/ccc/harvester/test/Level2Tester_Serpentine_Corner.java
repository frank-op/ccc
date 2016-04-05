package ccc.harvester.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import ccc.harvester.HarvestingCalculator;
import ccc.harvester.field.CornField;

public class Level2Tester_Serpentine_Corner {

	@Test
	public void test() {

		String harvestSerpentinesStartFromGivenCorner = HarvestingCalculator
				.harvestSerpentinesStartFromGivenCorner(new CornField(2, 5), 2, 1);
		System.out.println(harvestSerpentinesStartFromGivenCorner);
		Assert.assertEquals(harvestSerpentinesStartFromGivenCorner, "6 7 8 9 10 5 4 3 2 1 ");

		harvestSerpentinesStartFromGivenCorner = HarvestingCalculator
				.harvestSerpentinesStartFromGivenCorner(new CornField(5, 2), 5, 2);
		System.out.println(harvestSerpentinesStartFromGivenCorner);
		Assert.assertEquals(harvestSerpentinesStartFromGivenCorner, "10 9 7 8 6 5 3 4 2 1 ");

		harvestSerpentinesStartFromGivenCorner = HarvestingCalculator
				.harvestSerpentinesStartFromGivenCorner(new CornField(23, 12), 1, 12);
		Assert.assertEquals(harvestSerpentinesStartFromGivenCorner,
				"12 11 10 9 8 7 6 5 4 3 2 1 13 14 15 16 17 18 19 20 21 22 23 24 36 35 34 33 32 31 30 29 28 27 26 25 37 38 39 40 41 42 43 44 45 46 47 48 60 59 58 57 56 55 54 53 52 51 50 49 61 62 63 64 65 66 67 68 69 70 71 72 84 83 82 81 80 79 78 77 76 75 74 73 85 86 87 88 89 90 91 92 93 94 95 96 108 107 106 105 104 103 102 101 100 99 98 97 109 110 111 112 113 114 115 116 117 118 119 120 132 131 130 129 128 127 126 125 124 123 122 121 133 134 135 136 137 138 139 140 141 142 143 144 156 155 154 153 152 151 150 149 148 147 146 145 157 158 159 160 161 162 163 164 165 166 167 168 180 179 178 177 176 175 174 173 172 171 170 169 181 182 183 184 185 186 187 188 189 190 191 192 204 203 202 201 200 199 198 197 196 195 194 193 205 206 207 208 209 210 211 212 213 214 215 216 228 227 226 225 224 223 222 221 220 219 218 217 229 230 231 232 233 234 235 236 237 238 239 240 252 251 250 249 248 247 246 245 244 243 242 241 253 254 255 256 257 258 259 260 261 262 263 264 276 275 274 273 272 271 270 269 268 267 266 265 ");
		System.out.println(harvestSerpentinesStartFromGivenCorner);
	}
}