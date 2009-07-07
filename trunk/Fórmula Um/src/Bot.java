
public class Bot extends Car
{
	public Bot(int angle)
	{
		super(angle);
	}
	
	public int getFrame(int botNumber)
	{
		return this.getFrame() + 4 * botNumber;
	}

	public void updateAll()
	{
		if(this.getSpeed() < 7)
		this.speedUp();		
	}
	
	int[] position = {
				671, 170, 0,
				671, 170, 0,
				671, 170, 0,
				671, 170, 0,
				671, 170, 0,
				671, 170, 0,
				673, 170, 0,
				676, 170, 0,
				680, 170, 0,
				685, 170, 0,
				691, 170, 0,
				698, 170, 0,
				706, 170, 0,
				715, 170, 0,
				725, 170, 0,
				734, 170, 0,
				744, 170, 0,
				753, 170, 0,
				763, 170, 0,
				772, 170, 0,
				782, 170, 0,
				791, 170, 0,
				801, 170, 0,
				810, 170, 0,
				820, 170, 0,
				829, 170, 0,
				839, 170, 0,
				848, 170, 0,
				858, 170, 0,
				867, 170, 0,
				877, 170, 0,
				886, 170, 0,
				896, 170, 0,
				905, 170, 0,
				915, 170, 0,
				924, 170, 0,
				934, 170, 0,
				943, 170, 0,
				953, 170, 0,
				962, 170, 0,
				972, 170, 0,
				981, 170, 0,
				991, 170, 0,
				1000, 170, 0,
				1010, 170, 0,
				1019, 170, 0,
				1029, 170, 0,
				1038, 170, 0,
				1048, 170, 0,
				1057, 170, 0,
				1057, 170, 2,
				1066, 174, 0,
				1074, 178, 0,
				1083, 182, 0,
				1091, 186, 0,
				1100, 190, 0,
				1108, 194, 0,
				1117, 198, 0,
				1117, 198, 2,
				1123, 205, 0,
				1130, 213, 0,
				1136, 220, 0,
				1143, 228, 0,
				1149, 235, 0,
				1156, 243, 0,
				1162, 250, 0,
				1169, 258, 0,
				1175, 265, 0,
				1182, 273, 0,
				1188, 280, 0,
				1195, 288, 0,
				1201, 295, 0,
				1208, 303, 0,
				1214, 310, 0,
				1221, 318, 0,
				1227, 325, 0,
				1234, 333, 0,
				1240, 340, 0,
				1240, 340, 2,
				1243, 350, 0,
				1243, 350, 2,
				1243, 360, 0,
				1243, 370, 0,
				1243, 379, 0,
				1243, 389, 0,
				1243, 398, 0,
				1243, 408, 0,
				1243, 417, 0,
				1243, 427, 0,
				1243, 436, 0,
				1243, 446, 0,
				1243, 455, 0,
				1243, 465, 0,
				1243, 474, 0,
				1243, 484, 0,
				1243, 493, 0,
				1243, 503, 0,
				1243, 512, 0,
				1243, 522, 0,
				1243, 531, 0,
				1243, 541, 0,
				1243, 550, 0,
				1243, 560, 0,
				1243, 569, 0,
				1243, 579, 0,
				1243, 588, 0,
				1243, 598, 0,
				1243, 607, 0,
				1243, 617, 0,
				1243, 626, 0,
				1243, 636, 0,
				1243, 645, 0,
				1243, 655, 0,
				1243, 664, 0,
				1243, 674, 0,
				1243, 683, 0,
				1243, 693, 0,
				1243, 702, 0,
				1243, 712, 0,
				1243, 721, 0,
				1243, 731, 0,
				1243, 740, 0,
				1243, 750, 0,
				1243, 759, 0,
				1243, 769, 0,
				1243, 778, 0,
				1243, 788, 0,
				1243, 797, 0,
				1243, 807, 0,
				1243, 816, 0,
				1243, 826, 0,
				1243, 835, 0,
				1243, 845, 0,
				1243, 854, 0,
				1243, 864, 0,
				1243, 873, 0,
				1243, 883, 0,
				1243, 892, 0,
				1243, 902, 0,
				1243, 911, 0,
				1243, 921, 0,
				1243, 930, 0,
				1243, 940, 0,
				1243, 949, 0,
				1243, 959, 0,
				1243, 968, 0,
				1243, 978, 0,
				1243, 987, 0,
				1243, 997, 0,
				1243, 1006, 0,
				1243, 1016, 0,
				1243, 1025, 0,
				1243, 1035, 0,
				1243, 1044, 0,
				1243, 1054, 0,
				1243, 1063, 0,
				1243, 1073, 0,
				1243, 1082, 0,
				1243, 1092, 0,
				1243, 1101, 0,
				1243, 1101, 2,
				1239, 1111, 0,
				1235, 1120, 0,
				1231, 1130, 0,
				1227, 1139, 0,
				1223, 1149, 0,
				1219, 1158, 0,
				1215, 1168, 0,
				1211, 1177, 0,
				1207, 1187, 0,
				1207, 1187, 2,
				1200, 1194, 0,
				1192, 1202, 0,
				1185, 1209, 0,
				1177, 1217, 0,
				1170, 1224, 0,
				1162, 1232, 0,
				1155, 1239, 0,
				1147, 1247, 0,
				1140, 1254, 0,
				1132, 1262, 0,
				1125, 1269, 0,
				1125, 1269, 2,
				1115, 1273, 0,
				1115, 1273, 2,
				1105, 1274, 0,
				1095, 1275, 0,
				1086, 1276, 0,
				1076, 1277, 0,
				1067, 1278, 0,
				1057, 1279, 0,
				1048, 1280, 0,
				1038, 1281, 0,
				1029, 1282, 0,
				1019, 1283, 0,
				1019, 1283, 2,
				1010, 1280, 0,
				1000, 1277, 0,
				991, 1274, 0,
				981, 1271, 0,
				972, 1268, 0,
				962, 1265, 0,
				953, 1262, 0,
				943, 1259, 0,
				934, 1256, 0,
				934, 1256, 1,
				924, 1257, 0,
				924, 1257, 1,
				916, 1261, 0,
				906, 1265, 0,
				897, 1269, 0,
				887, 1273, 0,
				878, 1277, 0,
				868, 1281, 0,
				859, 1285, 0,
				859, 1285, 2,
				848, 1286, 0,
				839, 1287, 0,
				829, 1288, 0,
				820, 1289, 0,
				810, 1290, 0,
				801, 1291, 0,
				791, 1292, 0,
				791, 1292, 2,
				782, 1289, 0,
				772, 1286, 0,
				763, 1283, 0,
				753, 1280, 0,
				744, 1277, 0,
				734, 1274, 0,
				725, 1271, 0,
				715, 1268, 0,
				715, 1268, 1,
				706, 1269, 0,
				696, 1270, 0,
				687, 1271, 0,
				677, 1272, 0,
				668, 1273, 0,
				658, 1274, 0,
				649, 1275, 0,
				639, 1276, 0,
				630, 1277, 0,
				620, 1278, 0,
				611, 1279, 0,
				611, 1279, 2,
				601, 1276, 0,
				592, 1273, 0,
				582, 1270, 0,
				573, 1267, 0,
				563, 1264, 0,
				554, 1261, 0,
				554, 1261, 1,
				544, 1262, 0,
				544, 1262, 1,
				536, 1266, 0,
				526, 1270, 0,
				517, 1274, 0,
				507, 1278, 0,
				498, 1282, 0,
				488, 1286, 0,
				479, 1290, 0,
				479, 1290, 2,
				468, 1291, 0,
				468, 1291, 2,
				459, 1288, 0,
				449, 1285, 0,
				440, 1282, 0,
				430, 1279, 0,
				421, 1276, 0,
				411, 1273, 0,
				402, 1270, 0,
				392, 1267, 0,
				383, 1264, 0,
				373, 1261, 0,
				364, 1258, 0,
				364, 1258, 2,
				356, 1251, 0,
				356, 1251, 2,
				352, 1243, 0,
				348, 1234, 0,
				344, 1226, 0,
				340, 1217, 0,
				336, 1209, 0,
				332, 1200, 0,
				328, 1192, 0,
				324, 1183, 0,
				320, 1175, 0,
				316, 1166, 0,
				312, 1158, 0,
				308, 1149, 0,
				304, 1141, 0,
				300, 1132, 0,
				300, 1132, 1,
				293, 1126, 0,
				285, 1119, 0,
				278, 1113, 0,
				270, 1106, 0,
				263, 1100, 0,
				255, 1093, 0,
				248, 1087, 0,
				240, 1080, 0,
				233, 1074, 0,
				225, 1067, 0,
				225, 1067, 2,
				221, 1059, 0,
				221, 1059, 2,
				221, 1048, 0,
				221, 1039, 0,
				221, 1029, 0,
				221, 1020, 0,
				221, 1010, 0,
				221, 1001, 0,
				221, 991, 0,
				221, 982, 0,
				221, 972, 0,
				221, 963, 0,
				221, 953, 0,
				221, 944, 0,
				221, 934, 0,
				221, 925, 0,
				221, 915, 0,
				221, 906, 0,
				221, 896, 0,
				221, 887, 0,
				221, 877, 0,
				221, 868, 0,
				221, 858, 0,
				221, 849, 0,
				221, 839, 0,
				221, 830, 0,
				221, 820, 0,
				221, 811, 0,
				221, 801, 0,
				221, 792, 0,
				221, 782, 0,
				221, 773, 0,
				221, 763, 0,
				221, 754, 0,
				221, 744, 0,
				221, 735, 0,
				221, 725, 0,
				221, 716, 0,
				221, 706, 0,
				221, 697, 0,
				221, 687, 0,
				221, 678, 0,
				221, 668, 0,
				221, 659, 0,
				221, 649, 0,
				221, 640, 0,
				221, 630, 0,
				221, 621, 0,
				221, 611, 0,
				221, 602, 0,
				221, 592, 0,
				221, 583, 0,
				221, 573, 0,
				221, 564, 0,
				221, 554, 0,
				221, 545, 0,
				221, 535, 0,
				221, 526, 0,
				221, 516, 0,
				221, 507, 0,
				221, 497, 0,
				221, 488, 0,
				221, 478, 0,
				221, 469, 0,
				221, 459, 0,
				221, 450, 0,
				221, 440, 0,
				221, 431, 0,
				221, 421, 0,
				221, 412, 0,
				221, 402, 0,
				221, 393, 0,
				221, 383, 0,
				221, 374, 0,
				221, 364, 0,
				221, 355, 0,
				221, 355, 2,
				224, 346, 0,
				227, 338, 0,
				230, 329, 0,
				233, 321, 0,
				236, 312, 0,
				239, 304, 0,
				242, 295, 0,
				245, 287, 0,
				245, 287, 2,
				252, 280, 0,
				252, 280, 2,
				260, 277, 0,
				269, 274, 0,
				277, 271, 0,
				286, 268, 0,
				294, 265, 0,
				303, 262, 0,
				311, 259, 0,
				311, 259, 1,
				318, 252, 0,
				318, 252, 1,
				321, 244, 0,
				324, 235, 0,
				327, 227, 0,
				330, 218, 0,
				333, 210, 0,
				333, 210, 2,
				340, 203, 0,
				340, 203, 2,
				348, 200, 0,
				348, 200, 2,
				359, 200, 0,
				368, 200, 0,
				378, 200, 0,
				387, 200, 0,
				397, 200, 0,
				406, 200, 0,
				416, 200, 0,
				425, 200, 0,
				435, 200, 0,
				444, 200, 0,
				454, 200, 0,
				463, 200, 0,
				473, 200, 0,
				482, 200, 0,
				492, 200, 0,
				501, 200, 0,
				511, 200, 0,
				520, 200, 0,
				530, 200, 0,
				539, 200, 0,
				549, 200, 0,
				558, 200, 0,
				568, 200, 0,
				577, 200, 0,
				587, 200, 0,
				596, 200, 0,
				606, 200, 0,
				615, 200, 0,
				625, 200, 0,
				634, 200, 0,
				644, 200, 0,
				653, 200, 0,
				663, 200, 0,
				672, 200, 0,
				682, 200, 0,
				691, 200, 0,
				701, 200, 0,
				710, 200, 0,
				720, 200, 0,
				729, 200, 0,
				739, 200, 0,
				748, 200, 0,
				758, 200, 0,
				767, 200, 0,
				777, 200, 0,
				786, 200, 0,
				796, 200, 0,
				805, 200, 0,
				815, 200, 0,
				824, 200, 0,
				834, 200, 0,
				843, 200, 0,
				853, 200, 0,
				862, 200, 0,
				872, 200, 0,
				881, 200, 0,
				891, 200, 0,
				900, 200, 0,
				910, 200, 0,
				919, 200, 0,
				929, 200, 0,
				938, 200, 0,
				948, 200, 0,
				957, 200, 0,
				967, 200, 0,
				976, 200, 0,
				986, 200, 0,
				995, 200, 0,
				1005, 200, 0,
				1014, 200, 0,
				1024, 200, 0,
				1033, 200, 0,
				1043, 200, 0,
				1052, 200, 0,
				1062, 200, 0,
				1071, 200, 0,
				1081, 200, 0,
				1090, 200, 0,
				1100, 200, 0,
				1100, 200, 2,
				1108, 204, 0,
				1117, 208, 0,
				1125, 212, 0,
				1134, 216, 0,
				1142, 220, 0,
				1151, 224, 0,
				1159, 228, 0,
				1159, 228, 2,
				1166, 236, 0,
				1172, 243, 0,
				1179, 251, 0,
				1185, 258, 0,
				1192, 266, 0,
				1198, 273, 0,
				1205, 281, 0,
				1211, 288, 0,
				1218, 296, 0,
				1224, 303, 0,
				1231, 311, 0,
				1237, 318, 0,
				1244, 326, 0,
				1250, 333, 0,
				1250, 333, 2,
				1253, 343, 0,
				1253, 343, 2,
				1253, 353, 0,
				1253, 363, 0,
				1253, 372, 0,
				1253, 382, 0,
				1253, 391, 0,
				1253, 401, 0,
				1253, 410, 0,
				1253, 420, 0,
				1253, 429, 0,
				1253, 439, 0,
				1253, 448, 0,
				1253, 458, 0,
				1253, 467, 0,
				1253, 477, 0,
				1253, 486, 0,
				1253, 496, 0,
				1253, 505, 0,
				1253, 515, 0,
				1253, 524, 0,
				1253, 534, 0,
				1253, 543, 0,
				1253, 553, 0,
				1253, 562, 0,
				1253, 572, 0,
				1253, 581, 0,
				1253, 591, 0,
				1253, 600, 0,
				1253, 610, 0,
				1253, 619, 0,
				1253, 629, 0,
				1253, 638, 0,
				1253, 648, 0,
				1253, 657, 0,
				1253, 667, 0,
				1253, 676, 0,
				1253, 686, 0,
				1253, 695, 0,
				1253, 705, 0,
				1253, 714, 0,
				1253, 724, 0,
				1253, 733, 0,
				1253, 743, 0,
				1253, 752, 0,
				1253, 762, 0,
				1253, 771, 0,
				1253, 781, 0,
				1253, 790, 0,
				1253, 800, 0,
				1253, 809, 0,
				1253, 819, 0,
				1253, 828, 0,
				1253, 838, 0,
				1253, 847, 0,
				1253, 857, 0,
				1253, 866, 0,
				1253, 876, 0,
				1253, 885, 0,
				1253, 895, 0,
				1253, 904, 0,
				1253, 914, 0,
				1253, 923, 0,
				1253, 933, 0,
				1253, 942, 0,
				1253, 952, 0,
				1253, 961, 0,
				1253, 971, 0,
				1253, 980, 0,
				1253, 990, 0,
				1253, 999, 0,
				1253, 1009, 0,
				1253, 1018, 0,
				1253, 1028, 0,
				1253, 1037, 0,
				1253, 1047, 0,
				1253, 1056, 0,
				1253, 1066, 0,
				1253, 1075, 0,
				1253, 1075, 2,
				1249, 1085, 0,
				1245, 1094, 0,
				1241, 1104, 0,
				1237, 1113, 0,
				1233, 1123, 0,
				1229, 1132, 0,
				1225, 1142, 0,
				1221, 1151, 0,
				1221, 1151, 2,
				1213, 1159, 0,
				1206, 1166, 0,
				1198, 1174, 0,
				1191, 1181, 0,
				1183, 1189, 0,
				1176, 1196, 0,
				1168, 1204, 0,
				1161, 1211, 0,
				1153, 1219, 0,
				1146, 1226, 0,
				1138, 1234, 0,
				1131, 1241, 0,
				1123, 1249, 0,
				1116, 1256, 0,
				1108, 1264, 0,
				1108, 1264, 2,
				1099, 1268, 0,
				1099, 1268, 2,
				1088, 1269, 0,
				1079, 1270, 0,
				1069, 1271, 0,
				1060, 1272, 0,
				1050, 1273, 0,
				1041, 1274, 0,
				1031, 1275, 0,
				1022, 1276, 0,
				1012, 1277, 0,
				1012, 1277, 2,
				1003, 1274, 0,
				993, 1271, 0,
				984, 1268, 0,
				974, 1265, 0,
				965, 1262, 0,
				955, 1259, 0,
				946, 1256, 0,
				936, 1253, 0,
				927, 1250, 0,
				927, 1250, 1,
				917, 1251, 0,
				908, 1252, 0,
				898, 1253, 0,
				889, 1254, 0,
				879, 1255, 0,
				870, 1256, 0,
				860, 1257, 0,
				851, 1258, 0,
				841, 1259, 0,
				832, 1260, 0,
				822, 1261, 0,
				822, 1261, 2,
				813, 1258, 0,
				803, 1255, 0,
				794, 1252, 0,
				784, 1249, 0,
				775, 1246, 0,
				765, 1243, 0,
				756, 1240, 0,
				746, 1237, 0,
				746, 1237, 1,
				737, 1238, 0,
				727, 1239, 0,
				718, 1240, 0,
				708, 1241, 0,
				699, 1242, 0,
				689, 1243, 0,
				680, 1244, 0,
				670, 1245, 0,
				661, 1246, 0,
				651, 1247, 0,
				642, 1248, 0,
				632, 1249, 0,
				623, 1250, 0,
				613, 1251, 0,
				604, 1252, 0,
				594, 1253, 0,
				585, 1254, 0,
				575, 1255, 0,
				566, 1256, 0,
				556, 1257, 0,
				547, 1258, 0,
				537, 1259, 0,
				528, 1260, 0,
				518, 1261, 0,
				509, 1262, 0,
				499, 1263, 0,
				490, 1264, 0,
				480, 1265, 0,
				480, 1265, 2,
				471, 1262, 0,
				461, 1259, 0,
				452, 1256, 0,
				442, 1253, 0,
				433, 1250, 0,
				423, 1247, 0,
				414, 1244, 0,
				404, 1241, 0,
				404, 1241, 1,
				395, 1242, 0,
				385, 1243, 0,
				376, 1244, 0,
				366, 1245, 0,
				357, 1246, 0,
				357, 1246, 2,
				347, 1243, 0,
				338, 1240, 0,
				328, 1237, 0,
				319, 1234, 0,
				319, 1234, 2,
				311, 1227, 0,
				311, 1227, 2,
				307, 1219, 0,
				307, 1219, 2,
				307, 1208, 0,
				307, 1199, 0,
				307, 1189, 0,
				307, 1180, 0,
				307, 1170, 0,
				307, 1161, 0,
				307, 1151, 0,
				307, 1142, 0,
				307, 1142, 1,
				303, 1134, 0,
				303, 1134, 1,
				296, 1128, 0,
				296, 1128, 1,
				286, 1125, 0,
				277, 1122, 0,
				267, 1119, 0,
				258, 1116, 0,
				248, 1113, 0,
				248, 1113, 2,
				241, 1107, 0,
				233, 1100, 0,
				226, 1094, 0,
				218, 1087, 0,
				211, 1081, 0,
				203, 1074, 0,
				196, 1068, 0,
				188, 1061, 0,
				188, 1061, 2,
				184, 1053, 0,
				184, 1053, 2,
				184, 1042, 0,
				184, 1033, 0,
				184, 1023, 0,
				184, 1014, 0,
				184, 1004, 0,
				184, 995, 0,
				184, 985, 0,
				184, 976, 0,
				184, 966, 0,
				184, 957, 0,
				184, 947, 0,
				184, 938, 0,
				184, 928, 0,
				184, 919, 0,
				184, 909, 0,
				184, 900, 0,
				184, 890, 0,
				184, 881, 0,
				184, 871, 0,
				184, 862, 0,
				184, 852, 0,
				184, 843, 0,
				184, 833, 0,
				184, 824, 0,
				184, 814, 0,
				184, 805, 0,
				184, 795, 0,
				184, 786, 0,
				184, 776, 0,
				184, 767, 0,
				184, 757, 0,
				184, 748, 0,
				184, 738, 0,
				184, 729, 0,
				184, 719, 0,
				184, 710, 0,
				184, 700, 0,
				184, 691, 0,
				184, 681, 0,
				184, 672, 0,
				184, 662, 0,
				184, 653, 0,
				184, 643, 0,
				184, 634, 0,
				184, 624, 0,
				184, 615, 0,
				184, 605, 0,
				184, 596, 0,
				184, 586, 0,
				184, 577, 0,
				184, 567, 0,
				184, 558, 0,
				184, 548, 0,
				184, 539, 0,
				184, 529, 0,
				184, 520, 0,
				184, 510, 0,
				184, 501, 0,
				184, 491, 0,
				184, 482, 0,
				184, 472, 0,
				184, 463, 0,
				184, 453, 0,
				184, 444, 0,
				184, 434, 0,
				184, 425, 0,
				184, 415, 0,
				184, 406, 0,
				184, 396, 0,
				184, 387, 0,
				184, 377, 0,
				184, 377, 2,
				187, 369, 0,
				187, 369, 2,
				194, 362, 0,
				194, 362, 2,
				202, 359, 0,
				211, 356, 0,
				219, 353, 0,
				228, 350, 0,
				236, 347, 0,
				245, 344, 0,
				253, 341, 0,
				262, 338, 0,
				270, 335, 0,
				279, 332, 0,
				279, 332, 1,
				285, 326, 0,
				292, 319, 0,
				298, 313, 0,
				305, 306, 0,
				311, 300, 0,
				311, 300, 1,
				314, 291, 0,
				314, 291, 1,
				314, 282, 0,
				314, 272, 0,
				314, 263, 0,
				314, 253, 0,
				314, 244, 0,
				314, 244, 2,
				317, 235, 0,
				317, 235, 2,
				323, 229, 0,
				323, 229, 2,
				332, 226, 0,
				340, 223, 0,
				349, 220, 0,
				357, 217, 0,
				366, 214, 0,
				374, 211, 0,
				383, 208, 0,
				391, 205, 0,
				400, 202, 0,
				400, 202, 2,
				410, 202, 0,
				410, 202, 2,
				419, 206, 0,
				427, 210, 0,
				436, 214, 0,
				444, 218, 0,
				453, 222, 0,
				461, 226, 0,
				470, 230, 0,
				470, 230, 1,
				479, 230, 0,
				479, 230, 1,
				487, 227, 0,
				495, 224, 0,
				504, 221, 0,
				512, 218, 0,
				521, 215, 0,
				529, 212, 0,
				538, 209, 0,
				538, 209, 2,
				548, 209, 0,
				548, 209, 2,
				557, 213, 0,
				557, 213, 2,
				563, 220, 0,
				570, 228, 0,
				576, 235, 0,
				583, 243, 0,
				589, 250, 0,
				596, 258, 0,
				596, 258, 1,
				604, 262, 0,
				604, 262, 1,
				614, 262, 0,
				614, 262, 1,
				621, 259, 0,
				621, 259, 1,
				628, 252, 0,
				634, 246, 0,
				641, 239, 0,
				647, 233, 0,
				654, 226, 0,
				660, 220, 0,
				667, 213, 0,
				673, 207, 0,
				673, 207, 2,
				682, 204, 0,
				690, 201, 0,
				699, 198, 0,
				707, 195, 0,
				716, 192, 0,
				724, 189, 0,
				733, 186, 0,
				741, 183, 0,
				750, 180, 0,
				750, 180, 2,
				760, 180, 0,
				760, 180, 2,
				769, 184, 0,
				777, 188, 0,
				786, 192, 0,
				794, 196, 0,
				803, 200, 0,
				811, 204, 0,
				820, 208, 0,
				828, 212, 0,
				828, 212, 1,
				838, 212, 0,
				847, 212, 0,
				857, 212, 0,
				866, 212, 0,
				876, 212, 0,
				885, 212, 0,
				895, 212, 0,
				904, 212, 0,
				914, 212, 0,
				923, 212, 0,
				933, 212, 0,
				942, 212, 0,
				952, 212, 0,
				961, 212, 0,
				971, 212, 0,
				980, 212, 0,
				990, 212, 0,
				999, 212, 0,
				1009, 212, 0,
				1018, 212, 0,
				1028, 212, 0,
				1037, 212, 0,
				1047, 212, 0,
				1056, 212, 0,
				1066, 212, 0,
				1075, 212, 0,
				1075, 212, 2,
				1084, 216, 0,
				1084, 216, 2,
				1090, 223, 0,
				1097, 231, 0,
				1103, 238, 0,
				1110, 246, 0,
				1116, 253, 0,
				1123, 261, 0,
				1129, 268, 0,
				1136, 276, 0,
				1142, 283, 0,
				1149, 291, 0,
				1155, 298, 0,
				1162, 306, 0,
				1168, 313, 0,
				1175, 321, 0,
				1181, 328, 0,
				1188, 336, 0,
				1194, 343, 0,
				1201, 351, 0,
				1207, 358, 0,
				1207, 358, 2,
				1210, 368, 0,
				1210, 368, 2,
				1210, 378, 0,
				1210, 388, 0,
				1210, 397, 0,
				1210, 407, 0,
				1210, 416, 0,
				1210, 426, 0,
				1210, 435, 0,
				1210, 445, 0,
				1210, 454, 0,
				1210, 464, 0,
				1210, 473, 0,
				1210, 483, 0,
				1210, 492, 0,
				1210, 502, 0,
				1210, 511, 0,
				1210, 521, 0,
				1210, 530, 0,
				1210, 540, 0,
				1210, 549, 0,
				1210, 559, 0,
				1210, 568, 0,
				1210, 578, 0,
				1210, 587, 0,
				1210, 597, 0,
				1210, 606, 0,
				1210, 616, 0,
				1210, 625, 0,
				1210, 635, 0,
				1210, 644, 0,
				1210, 654, 0,
				1210, 663, 0,
				1210, 673, 0,
				1210, 682, 0,
				1210, 692, 0,
				1210, 701, 0,
				1210, 711, 0,
				1210, 720, 0,
				1210, 730, 0,
				1210, 739, 0,
				1210, 749, 0,
				1210, 758, 0,
				1210, 768, 0,
				1210, 777, 0,
				1210, 787, 0,
				1210, 796, 0,
				1210, 806, 0,
				1210, 815, 0,
				1210, 825, 0,
				1210, 834, 0,
				1210, 844, 0,
				1210, 853, 0,
				1210, 863, 0,
				1210, 872, 0,
				1210, 882, 0,
				1210, 891, 0,
				1210, 901, 0,
				1210, 910, 0,
				1210, 920, 0,
				1210, 929, 0,
				1210, 939, 0,
				1210, 948, 0,
				1210, 958, 0,
				1210, 967, 0,
				1210, 977, 0,
				1210, 986, 0,
				1210, 996, 0,
				1210, 1005, 0,
				1210, 1015, 0,
				1210, 1024, 0,
				1210, 1034, 0,
				1210, 1043, 0,
				1210, 1053, 0,
				1210, 1062, 0,
				1210, 1062, 2,
				1206, 1072, 0,
				1206, 1072, 2,
				1199, 1079, 0,
				1191, 1087, 0,
				1184, 1094, 0,
				1176, 1102, 0,
				1169, 1109, 0,
				1161, 1117, 0,
				1154, 1124, 0,
				1146, 1132, 0,
				1139, 1139, 0,
				1131, 1147, 0,
				1124, 1154, 0,
				1116, 1162, 0,
				1109, 1169, 0,
				1101, 1177, 0,
				1094, 1184, 0,
				1086, 1192, 0,
				1079, 1199, 0,
				1071, 1207, 0,
				1071, 1207, 2,
				1062, 1211, 0,
				1062, 1211, 2,
				1051, 1212, 0,
				1042, 1213, 0,
				1032, 1214, 0,
				1023, 1215, 0,
				1013, 1216, 0,
				1004, 1217, 0,
				994, 1218, 0,
				985, 1219, 0,
				975, 1220, 0,
				966, 1221, 0,
				956, 1222, 0,
				947, 1223, 0,
				937, 1224, 0,
				928, 1225, 0,
				918, 1226, 0,
				909, 1227, 0,
				899, 1228, 0,
				899, 1228, 2,
				890, 1225, 0,
				880, 1222, 0,
				871, 1219, 0,
				861, 1216, 0,
				852, 1213, 0,
				842, 1210, 0,
				833, 1207, 0,
				823, 1204, 0,
				823, 1204, 1,
				814, 1205, 0,
				804, 1206, 0,
				795, 1207, 0,
				785, 1208, 0,
				776, 1209, 0,
				766, 1210, 0,
				757, 1211, 0,
				747, 1212, 0,
				738, 1213, 0,
				728, 1214, 0,
				719, 1215, 0,
				709, 1216, 0,
				700, 1217, 0,
				690, 1218, 0,
				681, 1219, 0,
				671, 1220, 0,
				662, 1221, 0,
				652, 1222, 0,
				643, 1223, 0,
				633, 1224, 0,
				624, 1225, 0,
				614, 1226, 0,
				605, 1227, 0,
				595, 1228, 0,
				586, 1229, 0,
				576, 1230, 0,
				567, 1231, 0,
				557, 1232, 0,
				548, 1233, 0,
				538, 1234, 0,
				529, 1235, 0,
				519, 1236, 0,
				510, 1237, 0,
				500, 1238, 0,
				491, 1239, 0,
				481, 1240, 0,
				472, 1241, 0,
				462, 1242, 0,
				453, 1243, 0,
				453, 1243, 2,
				443, 1240, 0,
				434, 1237, 0,
				424, 1234, 0,
				415, 1231, 0,
				405, 1228, 0,
				396, 1225, 0,
				386, 1222, 0,
				377, 1219, 0,
				367, 1216, 0,
				358, 1213, 0,
				348, 1210, 0,
				339, 1207, 0,
				339, 1207, 2,
				331, 1200, 0,
				324, 1194, 0,
				316, 1187, 0,
				309, 1181, 0,
				301, 1174, 0,
				294, 1168, 0,
				286, 1161, 0,
				279, 1155, 0,
				271, 1148, 0,
				264, 1142, 0,
				256, 1135, 0,
				249, 1129, 0,
				241, 1122, 0,
				234, 1116, 0,
				226, 1109, 0,
				226, 1109, 2,
				222, 1101, 0,
				222, 1101, 2,
				222, 1090, 0,
				222, 1081, 0,
				222, 1071, 0,
				222, 1062, 0,
				222, 1052, 0,
				222, 1043, 0,
				222, 1033, 0,
				222, 1024, 0,
				222, 1014, 0,
				222, 1005, 0,
				222, 995, 0,
				222, 986, 0,
				222, 976, 0,
				222, 967, 0,
				222, 957, 0,
				222, 948, 0,
				222, 938, 0,
				222, 929, 0,
				222, 919, 0,
				222, 910, 0,
				222, 900, 0,
				222, 891, 0,
				222, 881, 0,
				222, 872, 0,
				222, 862, 0,
				222, 853, 0,
				222, 843, 0,
				222, 834, 0,
				222, 824, 0,
				222, 815, 0,
				222, 805, 0,
				222, 796, 0,
				222, 786, 0,
				222, 777, 0,
				222, 767, 0,
				222, 758, 0,
				222, 748, 0,
				222, 739, 0,
				222, 729, 0,
				222, 720, 0,
				222, 710, 0,
				222, 701, 0,
				222, 691, 0,
				222, 682, 0,
				222, 672, 0,
				222, 663, 0,
				222, 653, 0,
				222, 644, 0,
				222, 634, 0,
				222, 625, 0,
				222, 615, 0,
				222, 606, 0,
				222, 596, 0,
				222, 587, 0,
				222, 577, 0,
				222, 568, 0,
				222, 558, 0,
				222, 549, 0,
				222, 539, 0,
				222, 530, 0,
				222, 520, 0,
				222, 511, 0,
				222, 501, 0,
				222, 492, 0,
				222, 482, 0,
				222, 473, 0,
				222, 463, 0,
				222, 454, 0,
				222, 444, 0,
				222, 435, 0,
				222, 425, 0,
				222, 416, 0,
				222, 406, 0,
				222, 397, 0,
				222, 387, 0,
				222, 378, 0,
				222, 368, 0,
				222, 368, 2,
				225, 360, 0,
				228, 351, 0,
				231, 343, 0,
				234, 334, 0,
				237, 326, 0,
				240, 317, 0,
				243, 309, 0,
				246, 300, 0,
				249, 292, 0,
				249, 292, 2,
				256, 285, 0,
				262, 279, 0,
				269, 272, 0,
				275, 266, 0,
				282, 259, 0,
				288, 253, 0,
				295, 246, 0,
				301, 240, 0,
				308, 233, 0,
				314, 227, 0,
				321, 220, 0,
				327, 214, 0,
				334, 207, 0,
				340, 201, 0,
				340, 201, 2,
				349, 198, 0,
				349, 198, 2,
				359, 198, 0,
				369, 198, 0,
				378, 198, 0,
				388, 198, 0,
				397, 198, 0,
				407, 198, 0,
				416, 198, 0,
				426, 198, 0,
				435, 198, 0,
				445, 198, 0,
				454, 198, 0,
				464, 198, 0,
				473, 198, 0,
				483, 198, 0,
				492, 198, 0,
				502, 198, 0,
				511, 198, 0,
				521, 198, 0,
				530, 198, 0,
				540, 198, 0,
				549, 198, 0,
				559, 198, 0,
				568, 198, 0,
				578, 198, 0,
				587, 198, 0,
				597, 198, 0,
				606, 198, 0,
				616, 198, 0,
				625, 198, 0,
				635, 198, 0,
				644, 198, 0,
				654, 198, 0,
				663, 198, 0,
				673, 198, 0,
				682, 198, 0,
				692, 198, 0,
				701, 198, 0,
				711, 198, 0,
				720, 198, 0,
				730, 198, 0,
				739, 198, 0,
				749, 198, 0,
				758, 198, 0,
				768, 198, 0,
				777, 198, 0,
				787, 198, 0,
				796, 198, 0,
				806, 198, 0,
				815, 198, 0,
				825, 198, 0,
				834, 198, 0,
				844, 198, 0,
				853, 198, 0,
				863, 198, 0,
				872, 198, 0,
				882, 198, 0,
				891, 198, 0,
				901, 198, 0,
				910, 198, 0,
				920, 198, 0,
				929, 198, 0,
				939, 198, 0,
				948, 198, 0,
				958, 198, 0,
				967, 198, 0,
				977, 198, 0,
				986, 198, 0,
				996, 198, 0,
				1005, 198, 0,
				1015, 198, 0,
				1024, 198, 0,
				1034, 198, 0,
				1043, 198, 0,
				1053, 198, 0,
				1062, 198, 0,
				1070, 198, 0,
				1077, 198, 0,
				1083, 198, 0,
				1088, 198, 0,
				1092, 198, 0,
				1095, 198, 0,
				1097, 198, 0,
				1098, 198, 0,
				1098, 198, 0,
				1098, 198, 0,
				1098, 198, 0,
				1098, 198, 0,
				1098, 198, 0,
				1098, 198, 0,
				1098, 198, 0,
				1098, 198, 0,
				1098, 198, 0,
				1098, 198, 0,
				1098, 198, 0,
				1098, 198, 0,
				1098, 198, 0,
				1098, 198, 0,
				1098, 198, 0,
				1098, 198, 0,
				1098, 198, 0,
				1098, 198, 0,
				1098, 198, 0,
				1098, 198, 0,
				1098, 198, 0,
				1098, 198, 0,
				1098, 198, 0,
				1098, 198, 0,
				1098, 198, 0,
				1098, 198, 0,
				1098, 198, 0,
				1098, 198, 0,
				1098, 198, 0,
				1098, 198, 0};
}
