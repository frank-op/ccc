package ccc.harvester.level;

import org.testng.Assert;
import org.testng.annotations.Test;

import ccc.harvester.HarvestingScenarioExecutor;
import ccc.harvester.HarvestingScenarioExecutor.Direction2;
import ccc.harvester.field.CornField;

public class Level3Columns {

	@Test
	public void test() {
		String harvestSerpentinesStartFromGivenCornerColumns = HarvestingScenarioExecutor
				.harvestSerpentinesStartFromGivenCornerColumns(new CornField(3, 4), Direction2.SOUTH, 1, 1);
		System.out.println(harvestSerpentinesStartFromGivenCornerColumns);
		Assert.assertEquals(harvestSerpentinesStartFromGivenCornerColumns, "1 5 9 10 6 2 3 7 11 12 8 4 ");

		harvestSerpentinesStartFromGivenCornerColumns = HarvestingScenarioExecutor
				.harvestSerpentinesStartFromGivenCornerColumns(new CornField(5, 2), Direction2.NORTH, 5, 2);
		System.out.println(harvestSerpentinesStartFromGivenCornerColumns);
		Assert.assertEquals(harvestSerpentinesStartFromGivenCornerColumns, "10 8 6 4 2 1 3 5 7 9 ");

		harvestSerpentinesStartFromGivenCornerColumns = HarvestingScenarioExecutor
				.harvestSerpentinesStartFromGivenCornerColumns(new CornField(23, 12), Direction2.NORTH, 23, 1);
		Assert.assertEquals(harvestSerpentinesStartFromGivenCornerColumns,
				"265 253 241 229 217 205 193 181 169 157 145 133 121 109 97 85 73 61 49 37 25 13 1 2 14 26 38 50 62 74 86 98 110 122 134 146 158 170 182 194 206 218 230 242 254 266 267 255 243 231 219 207 195 183 171 159 147 135 123 111 99 87 75 63 51 39 27 15 3 4 16 28 40 52 64 76 88 100 112 124 136 148 160 172 184 196 208 220 232 244 256 268 269 257 245 233 221 209 197 185 173 161 149 137 125 113 101 89 77 65 53 41 29 17 5 6 18 30 42 54 66 78 90 102 114 126 138 150 162 174 186 198 210 222 234 246 258 270 271 259 247 235 223 211 199 187 175 163 151 139 127 115 103 91 79 67 55 43 31 19 7 8 20 32 44 56 68 80 92 104 116 128 140 152 164 176 188 200 212 224 236 248 260 272 273 261 249 237 225 213 201 189 177 165 153 141 129 117 105 93 81 69 57 45 33 21 9 10 22 34 46 58 70 82 94 106 118 130 142 154 166 178 190 202 214 226 238 250 262 274 275 263 251 239 227 215 203 191 179 167 155 143 131 119 107 95 83 71 59 47 35 23 11 12 24 36 48 60 72 84 96 108 120 132 144 156 168 180 192 204 216 228 240 252 264 276 ");
		System.out.println(harvestSerpentinesStartFromGivenCornerColumns);
	}
}