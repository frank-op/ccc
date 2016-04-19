package ccc.addictiveGame;

import static ccc.addictiveGame.execute.Executor.executeFindManhattenDistance;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Level2_ManhattenPath {

	@Test
	public void test1() {
		String result = executeFindManhattenDistance("6 5 8 1 4 5 2 7 3 12 4 17 3 19 1 24 1 26 2");
		System.out.println(result);
		Assert.assertEquals("1 9 2 3 ", result);
	}

	@Test
	public void test2() {
		String result = executeFindManhattenDistance("63 75 6 4518 1 4711 1 1120 2 4430 2 2029 3 1787 3 ");
		System.out.println(result);
		Assert.assertEquals("45 110 62 ", result);
	}

	@Test
	public void test3() {
		String result = executeFindManhattenDistance(
				"563 175 350 43971 1 64769 1 22790 2 17607 2 56680 3 8079 3 3491 4 93953 4 80732 5 53945 5 79053 6 11995 6 66082 7 81757 7 72805 8 84256 8 73796 9 92504 9 40411 10 19319 10 70916 11 23693 11 70648 12 64724 12 83915 13 54332 13 5074 14 5069 14 97478 15 12667 15 51768 16 79311 16 64652 17 39000 17 38217 18 3917 18 96706 19 90062 19 78729 20 47760 20 7103 21 24665 21 71023 22 24999 22 76429 23 46169 23 96305 24 23381 24 85923 25 45943 25 11800 26 71329 26 86642 27 40841 27 19883 28 73163 28 11799 29 61535 29 29923 30 97088 30 49363 31 66969 31 7252 32 74583 32 44716 33 27616 33 20756 34 36115 34 36838 35 48278 35 46933 36 84113 36 30644 37 48481 37 35397 38 95565 38 11573 39 29781 39 29135 40 31695 40 76447 41 72780 41 86667 42 13082 42 90116 43 7793 43 82227 44 88744 44 52006 45 17562 45 3954 46 13768 46 40128 47 79829 47 8967 48 74869 48 54116 49 56355 49 61656 50 45419 50 79622 51 60449 51 48603 52 48413 52 96770 53 77352 53 92059 54 45670 54 91870 55 42719 55 48495 56 93062 56 65536 57 30812 57 9476 58 70348 58 87876 59 96352 59 59455 60 78459 60 83953 61 36635 61 28653 62 38678 62 81083 63 71103 63 38070 64 16059 64 38051 65 85053 65 12484 66 80345 66 34659 67 64192 67 50191 68 63907 68 16680 69 8827 69 44530 70 51109 70 72256 71 8830 71 68697 72 29374 72 79130 73 72794 73 28606 74 83873 74 17250 75 32625 75 43652 76 73028 76 76764 77 8522 77 5740 78 13687 78 38020 79 67372 79 81005 80 53972 80 58764 81 17784 81 74339 82 37334 82 55829 83 70808 83 54705 84 63686 84 24759 85 38641 85 67225 86 17002 86 18456 87 61594 87 72986 88 77844 88 50595 89 5948 89 28518 90 56564 90 65876 91 58580 91 88333 92 82466 92 38476 93 74724 93 28074 94 59083 94 82733 95 45837 95 63414 96 48615 96 9224 97 90335 97 70546 98 12415 98 5885 99 37868 99 8270 100 61574 100 73291 101 49683 101 32129 102 37896 102 27018 103 9244 103 64534 104 75794 104 56144 105 55288 105 14883 106 83800 106 21728 107 47656 107 75202 108 2158 108 22640 109 17114 109 8200 110 54630 110 30497 111 8120 111 34183 112 62878 112 94752 113 75015 113 14991 114 9472 114 26713 115 6083 115 42933 116 26513 116 46741 117 21977 117 83993 118 21787 118 21192 119 76730 119 60766 120 12825 120 91098 121 96226 121 40939 122 32910 122 43515 123 91059 123 19856 124 12756 124 4671 125 72650 125 34697 126 42999 126 68220 127 57304 127 50292 128 63275 128 46513 129 86082 129 72229 130 32247 130 25898 131 54353 131 4062 132 59097 132 37742 133 53133 133 35442 134 37589 134 56543 135 66032 135 4727 136 31281 136 44517 137 65202 137 4137 138 70489 138 47278 139 60139 139 61709 140 57012 140 29875 141 98082 141 85959 142 46477 142 25834 143 30997 143 57500 144 30577 144 23538 145 2909 145 79298 146 19485 146 74769 147 5684 147 62195 148 59654 148 50119 149 50416 149 54692 150 20719 150 28431 151 36718 151 39845 152 87764 152 59675 153 8528 153 41918 154 52202 154 71969 155 58108 155 78979 156 76976 156 63250 157 82647 157 95838 158 29640 158 37089 159 16856 159 19224 160 30880 160 1787 161 30823 161 87043 162 89107 162 47193 163 78818 163 94673 164 91827 164 95128 165 74213 165 25178 166 11335 166 80004 167 41619 167 84877 168 30078 168 44191 169 94261 169 20432 170 47610 170 58740 171 69883 171 11833 172 38386 172 69621 173 17755 173 75862 174 66590 174 94503 175 58356 175 ");
		System.out.println(result);
		Assert.assertEquals(
				"146 97 403 530 165 416 165 141 124 212 297 60 177 5 549 265 220 196 44 183 162 264 188 468 308 369 311 400 320 419 206 429 148 129 130 292 115 376 112 80 29 511 543 171 228 70 251 478 49 131 207 16 278 427 431 371 272 494 124 180 338 183 62 165 342 427 301 188 67 109 438 277 176 349 113 192 398 149 294 237 264 291 189 107 136 289 334 160 409 290 96 125 230 211 240 161 553 477 225 408 152 41 178 180 24 535 176 486 106 386 151 169 151 125 138 124 228 436 380 283 82 199 328 116 485 124 128 107 245 310 233 399 97 59 93 280 153 406 159 55 433 294 117 181 139 379 435 99 55 217 109 305 339 100 115 109 139 426 223 136 180 48 231 62 209 97 335 465 306 278 121 199 362 56 285 ",
				result);
	}

	@Test
	public void test4() {
		String result = executeFindManhattenDistance(
				"187 520 550 50998 1 49213 1 82598 2 70012 2 16435 3 83990 3 38159 4 78998 4 47221 5 79545 5 3732 6 74114 6 50222 7 27965 7 10567 8 80960 8 90728 9 10146 9 9655 10 34829 10 82177 11 90110 11 91843 12 96954 12 8739 13 85652 13 89951 14 31433 14 45927 15 34603 15 49295 16 69948 16 31042 17 19056 17 60088 18 22258 18 8832 19 9633 19 63382 20 85894 20 72589 21 93288 21 70664 22 13573 22 92795 23 7320 23 92616 24 92348 24 63869 25 83910 25 21270 26 75164 26 12057 27 77743 27 87572 28 31211 28 19452 29 86858 29 29997 30 87424 30 20920 31 59178 31 18382 32 24091 32 37505 33 451 33 28390 34 24814 34 27293 35 67246 35 41610 36 36220 36 25985 37 14010 37 62969 38 59805 38 81408 39 53489 39 31918 40 35531 40 50118 41 44156 41 6508 42 93700 42 48131 43 35716 43 60108 44 56172 44 62036 45 91454 45 92577 46 54342 46 57627 47 79674 47 1293 48 41982 48 27446 49 34825 49 57460 50 41327 50 3213 51 50448 51 21510 52 42716 52 39133 53 56189 53 435 54 5085 54 82890 55 58077 55 19510 56 48800 56 15553 57 28039 57 53732 58 47761 58 16995 59 16033 59 28686 60 1208 60 2829 61 83885 61 68305 62 43470 62 18588 63 30615 63 71095 64 79811 64 4790 65 55830 65 87422 66 19910 66 21964 67 46932 67 1663 68 88226 68 22420 69 9428 69 96481 70 17858 70 24115 71 26463 71 68732 72 93793 72 61176 73 66299 73 54177 74 74536 74 90043 75 88801 75 86598 76 51517 76 45140 77 87369 77 77344 78 5884 78 57688 79 24804 79 43225 80 26485 80 66813 81 80222 81 47169 82 10865 82 78119 83 26590 83 7464 84 57570 84 20644 85 56483 85 94441 86 60951 86 91209 87 87044 87 31907 88 27089 88 94704 89 92492 89 53225 90 53332 90 19321 91 87601 91 45465 92 24341 92 75989 93 21568 93 32866 94 35277 94 66569 95 69710 95 84022 96 45831 96 19735 97 95613 97 42055 98 91764 98 11857 99 72561 99 78398 100 35936 100 16287 101 23862 101 34822 102 53904 102 18664 103 47312 103 6303 104 35395 104 1604 105 81007 105 1775 106 97026 106 12800 107 86845 107 14450 108 96693 108 31507 109 60772 109 76610 110 26916 110 88149 111 81993 111 47299 112 36358 112 96512 113 16311 113 47813 114 46682 114 48894 115 7418 115 79772 116 42640 116 64018 117 70921 117 58748 118 62538 118 89043 119 87399 119 22133 120 75068 120 76906 121 15030 121 18558 122 37829 122 11796 123 88664 123 78198 124 94337 124 55883 125 26915 125 33230 126 45581 126 18584 127 40480 127 28171 128 12127 128 32139 129 61425 129 8583 130 90663 130 55179 131 43413 131 23895 132 68301 132 65792 133 67769 133 51055 134 31672 134 93510 135 45100 135 14777 136 62768 136 27092 137 37239 137 95860 138 45954 138 17970 139 43607 139 35961 140 54830 140 79818 141 29809 141 49637 142 29324 142 56144 143 7619 143 16404 144 62381 144 23542 145 86354 145 4008 146 94235 146 5229 147 48906 147 5917 148 45391 148 75232 149 62067 149 75999 150 12165 150 51292 151 68486 151 33628 152 19983 152 49292 153 7412 153 83604 154 6381 154 36221 155 14622 155 23278 156 83313 156 45592 157 46151 157 6096 158 1543 158 69469 159 16590 159 2482 160 60674 160 21497 161 42584 161 54224 162 15850 162 1247 163 67378 163 36522 164 20257 164 70812 165 28892 165 47242 166 87540 166 30098 167 24440 167 88786 168 57695 168 95939 169 11834 169 31927 170 82537 170 69832 171 12930 171 44701 172 71111 172 75421 173 65252 173 68761 174 80236 174 65298 175 36025 175 68501 176 84582 176 3177 177 95702 177 45307 178 38652 178 47046 179 52786 179 24370 180 88867 180 60528 181 80918 181 76102 182 54176 182 36987 183 16108 183 79063 184 16368 184 38935 185 95946 185 31696 186 94179 186 79361 187 35720 187 42121 188 87262 188 69756 189 73004 189 42236 190 72480 190 91108 191 79991 191 49729 192 42053 192 31863 193 54073 193 66670 194 2742 194 50744 195 23423 195 19703 196 72097 196 3152 197 56813 197 63277 198 85892 198 89771 199 31118 199 50398 200 87909 200 14165 201 5808 201 66018 202 44229 202 72855 203 8245 203 68685 204 30364 204 25482 205 12891 205 58853 206 44158 206 33287 207 76493 207 57514 208 24794 208 40200 209 76011 209 31629 210 65515 210 50174 211 62293 211 3830 212 32351 212 30912 213 30173 213 21774 214 85733 214 5677 215 74633 215 2389 216 59395 216 65703 217 44927 217 74761 218 80582 218 41108 219 33484 219 39525 220 46659 220 21306 221 59077 221 36817 222 35522 222 81222 223 65527 223 74747 224 70210 224 49721 225 31073 225 40378 226 67543 226 41807 227 17407 227 58299 228 81232 228 50317 229 72938 229 56778 230 49821 230 40158 231 66186 231 53711 232 18006 232 13723 233 87245 233 96746 234 86087 234 40709 235 83252 235 87832 236 23344 236 21854 237 7020 237 29088 238 29526 238 15960 239 21026 239 25465 240 34012 240 63944 241 37179 241 25070 242 18197 242 94777 243 41826 243 67721 244 28409 244 36608 245 22128 245 53642 246 82394 246 1580 247 79631 247 9306 248 10467 248 37965 249 69897 249 30710 250 80678 250 26986 251 56607 251 20406 252 15904 252 25434 253 87009 253 53476 254 1274 254 84572 255 67466 255 95002 256 70015 256 43498 257 43612 257 33589 258 38661 258 9640 259 73879 259 2862 260 72962 260 40459 261 69241 261 75783 262 49309 262 25692 263 94251 263 34379 264 76581 264 57187 265 55936 265 18309 266 59010 266 78841 267 41443 267 31226 268 67806 268 72502 269 6980 269 87467 270 89584 270 78696 271 15142 271 67083 272 72613 272 10765 273 67184 273 69399 274 5251 274 2776 275 36998 275 ");
		System.out.println(result);
		Assert.assertEquals(
				"299 130 175 357 146 317 146 328 173 262 148 99 195 390 138 187 49 203 241 412 141 520 359 253 278 290 292 309 324 404 371 22 458 71 164 341 38 50 215 34 290 336 89 303 354 319 356 207 113 44 176 155 137 39 195 226 30 281 80 135 220 173 90 141 178 218 56 409 33 254 272 149 87 118 321 308 493 357 187 453 137 166 148 282 110 274 13 147 393 107 291 237 284 335 27 304 188 307 253 423 309 195 103 84 515 274 458 241 201 322 96 42 275 93 204 380 156 378 87 207 123 68 240 50 208 153 98 107 411 238 217 400 107 415 143 243 286 110 206 185 185 72 258 305 229 427 87 122 190 249 67 151 360 411 320 401 40 136 263 160 327 180 218 407 281 340 73 169 297 267 408 161 251 57 209 70 213 118 31 141 149 128 482 346 299 558 123 507 134 142 345 139 412 155 333 227 204 309 220 522 53 510 515 233 434 414 129 103 138 151 182 134 220 124 337 304 64 112 191 387 262 257 125 385 108 177 87 97 303 337 78 244 343 282 179 132 275 83 144 310 296 421 191 284 108 207 201 402 273 144 76 187 424 302 87 75 114 138 365 235 394 97 213 163 213 219 114 250 128 41 236 340 370 311 164 ",
				result);
	}
}