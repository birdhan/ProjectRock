/*
 Navicat Premium Data Transfer

 Source Server         : han
 Source Server Type    : MySQL
 Source Server Version : 50639
 Source Host           : localhost:3306
 Source Schema         : byording

 Target Server Type    : MySQL
 Target Server Version : 50639
 File Encoding         : 65001

 Date: 30/09/2018 14:01:19
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_dish
-- ----------------------------
DROP TABLE IF EXISTS `t_dish`;
CREATE TABLE `t_dish`  (
  `did` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `dishname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `price` double NULL DEFAULT NULL,
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `heat` int(11) NULL DEFAULT NULL,
  `details` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `picture` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `animation` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`did`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_dish
-- ----------------------------
INSERT INTO `t_dish` VALUES ('1B0F7671D6294882AB1D113987B87042', '烧鸭', '挂炉烧鸭', 88, 'meiwei', 10, ' 嫩而不上，油而不腻', '42817703_3.jpg243abf01-bdc5-4b66-88a2-1b2b8b3f1df1.jpg', 'bounceInDown');
INSERT INTO `t_dish` VALUES ('344E1C075E14418E9B760EF72725BD00', '意大利面', '意粉', 55, 'zhushi', 22, '作为意大利面的法定原料，杜兰小麦是最硬质的小麦品种，具有高密度、高蛋白质、高筋度等特点，其制成的意大利面通体呈黄色，耐煮', 'u=1411909885,2274277729&fm=27&gp=0.jpg1bab2d6d-ce29-40bd-83fb-e9f5b16d4785.jpg', 'bounceInRight');
INSERT INTO `t_dish` VALUES ('69C304417E3B486C9C55ADA8A63707BA', '糖醋鲤鱼', '糖醋鲤鱼', 98, 'meiwei', 99, ' 嫩而多汁，味道鲜美', '42817703_4.jpg58c78f4e-d036-419a-b553-413b9a8faccd.jpg', 'bounceInRight');
INSERT INTO `t_dish` VALUES ('6B830973D7EA4A5FB8F624E75F735DDA', '杭州东坡肉', '特色', 48, 'meiwei', 98, '这是一道传统名菜。慢火焖烧的肉皮及肥肉酥烂，甜香，肥而不腻。', '鏉窞涓滃潯鑲�.jpg2422a788-e546-4f1f-8ee7-1054cd47786c.jpg', 'bounceInRight');
INSERT INTO `t_dish` VALUES ('6D53DD78323C4D6581B0C28AFCF3B649', '沙拉', '特色沙拉', 75, 'cantian', 17, ' 制出的沙拉奶香味浓郁，甜味加重。', 'timg3YNP2NYZ.jpg4f32f799-cb0f-494a-93c1-70f8de7c3db9.jpg', 'bounceInRight');
INSERT INTO `t_dish` VALUES ('91693A4C72634CB780F34F5DE7D1340F', '珍珠汤', '珍珠翡翠汤', 65, 'zhushi', 24, ' 珍珠汤是一道清香、甜美的汤品。', 'timgMA172UVN.jpgd691c0b9-cd9b-4e16-9a7e-e0a20622fcdf.jpg', 'bounceInUp');
INSERT INTO `t_dish` VALUES ('9BFB0ABA26DF4EA1BB766D7897865A48', '82年拉菲', '拉菲红酒', 89999, 'cantian', 5, ' 在拉菲每2至3棵葡萄树才能生产一瓶750ml的酒。拉菲庄还是愿花重本雇用最顶级酿酒大师的名庄。', 'u=3964614737,2899604960&fm=27&gp=0.jpgda25ded7-23f4-42f8-ba47-ecd1be78a5a6.jpg', 'bounceInRight');
INSERT INTO `t_dish` VALUES ('A1650815A36C4D87AD168DB669068332', '烤乳猪', '鲜美乳猪', 168, 'meiwei', 100, ' 鲜美佳肴，吸收了各种烹饪原料及烹饪厨艺', '鐑ょ尓.jpg60e8e7c3-578f-484a-9b6d-42a20513868d.jpg', 'bounceInLeft');
INSERT INTO `t_dish` VALUES ('A1A82E0ED30449A7B5706CF27DB82063', '辣子鸡', '麻辣辣子鸡', 48, 'meiwei', 13, ' 一道经典的川渝地区的特色传统名肴，营养丰富，味道鲜美，菜色泽棕红油亮，麻辣味浓。咸鲜醇香，略带回甜。', 'timg.jpgc3260c89-6595-48cd-884c-e794cdc50a50.jpg', 'bounceInRight');
INSERT INTO `t_dish` VALUES ('AFC47E765A5341D881CB9152712B462B', '冰沙土豆泥', '甜食', 33, 'cantian', 2, '炎炎夏日，来一杯可口的冰沙是很不错的哦！甜甜的，凉凉的！很不错哦！', '鍐版矙鍦熻眴娉�.jpeg6408760f-54e9-4bd0-9dd9-e959e47e5330.jpg', 'bounceInLeft');
INSERT INTO `t_dish` VALUES ('AFEA07A292F4425E99B9831A6F961A8D', '双腊煲仔饭', '主食', 28, 'zhushi', 2, ' 煲仔饭，顾名思义，就是用小煲仔煮的饭，在广东它是再大众不过了。老广有句话说：秋风起、吃腊味，巷头香到巷尾！', '鐓蹭粩楗�.jpg6edb6314-cc06-4b0c-89fa-c78b19c14c08.jpg', 'bounceInDown');
INSERT INTO `t_dish` VALUES ('B000A6959BC54C6EA98558FE12ED4146', '蜜汁叉烧包', '主食', 25, 'zhushi', 3, ' 蜜汁叉烧包，是广东特色早茶点心。以前看香港的电影电视，总会看到茶楼的片段，里面的小妹吆喝着“虾饺、叉烧包……”。', '鍙夌儳鍖�.jpg471c9058-28c3-4b8b-996f-67e2f86ff637.jpg', 'bounceInRight');
INSERT INTO `t_dish` VALUES ('BC09197BF0034C1FAD5183CCB56D0045', '水果捞', '水果捞', 35, 'cantian', 9, '“ 水果捞是一种制作简单，而且人见人爱的甜品。尤其是小姑娘，爱吃甜食的朋友更是爱不释手呢。甜甜的水果捞，', 'timgCHOWP136.jpg0606906b-2e90-4905-a4f1-b0b18d7b6b39.jpg', 'bounceInRight');
INSERT INTO `t_dish` VALUES ('C5BEF765BBD241C5A55624B1809F2DD0', '糯米糖藕', '甜食', 36, 'meiwei', 3, ' 老早家里大人熬煮糖藕,印象中用的是粗拉拉的片糖,那种香甜,可谓沁人心脾.', '绯背绯栬棔.jpgcba72281-a6b5-4db8-a633-58b0ffe18599.jpg', 'bounceInUp');
INSERT INTO `t_dish` VALUES ('CAA0FA12D12C49EDB64638B52F3C5885', '百岁山矿泉水', '百岁山天然矿泉水', 10, 'cantian', 20, ' 百岁山拥有优质的矿泉资源，富含了偏硅酸锂、锶、钙、钾等多种有益于人体健康的微量元素。', 'u=2627825271,2421331865&fm=27&gp=0.jpga80f0247-c7c8-4dbb-83c9-cfab76d4808b.jpg', 'bounceInLeft');
INSERT INTO `t_dish` VALUES ('CDB05B7272CC4C11870D3AC00DE10747', '蝉蛹', '开边蝉蛹', 88, 'meiwei', 28, ' 营养美味，具有很高的营养价值。', 'u=4187331828,291634662&fm=27&gp=0.jpg8906434d-c85c-44be-a3e1-a7225e6bda8c.jpg', 'bounceInUp');
INSERT INTO `t_dish` VALUES ('D07DB78783C24C039FBC8E47789211D8', '香菇虾仁糯米烧麦', '主食', 38, 'zhushi', 3, ' 烧麦又称烧卖、稍美、肖米、稍麦、稍梅、烧梅、鬼蓬头，是形容顶端蓬松束折如花的形状，是一种以烫面为皮裹馅上笼蒸熟的小吃。', '鐑ч害.jpgf1746339-13cf-4b5d-8b6b-f8e338e0ea50.jpg', 'bounceInUp');
INSERT INTO `t_dish` VALUES ('D0A5AC8840D84AD29DE2597BDC4E482D', '星巴克咖啡', '美国连锁店星巴克', 203, 'cantian', 6, ' 星巴克（Starbucks）是美国一家连锁咖啡公司的名称，1971年成立，是全球最大的咖啡连锁店', 'timgOKBO992M.jpg74160a79-eabd-4b07-93dd-561208f0e136.jpg', 'bounceInRight');
INSERT INTO `t_dish` VALUES ('D8FEA454548147E08BAAD9E99C80BB90', '快捷龙井虾仁', '美味', 66, 'meiwei', 2, ' 龙井茶清香扑鼻，河虾鲜美嫩滑，搭配成别具特色的江南名菜。此道菜肴富含维生素A、E，以及钙、磷、镁、锌、钾、铁。', '蹇嵎榫欎簳铏句粊.jpg85285605-52d0-4bfd-8e4e-fe9e5f29cc6f.jpg', 'bounceInUp');
INSERT INTO `t_dish` VALUES ('DE096F5B14784F978B2CA53DFAF8A308', '冷面', '冷面', 35, 'zhushi', 24, ' 朝鲜冷面的口味相对比较清淡，汤汁较多。\r\n\r\n\r\n\r\n\r\n ', '63d0f703918fa0ecb44937222b9759ee3d6ddb17.jpgff0f2395-e574-41b3-a44e-0e7cfc4581e6.jpg', 'bounceInUp');
INSERT INTO `t_dish` VALUES ('ED2B4B0B6B194C6696C12B96D53E6A33', '菠萝咕咾肉', '特色', 56, 'meiwei', 2, ' 菠萝咕咾肉是一道广东的传统名菜，属于粤菜系。其口感清新解腻，酸甜的味道能增进食欲，无论下酒还是下饭，都非常美味。', '鑿犺悵鍜曞捑鑲�.jpg0897175e-578c-4d6f-b475-24fd3e5d6e5f.jpg', 'bounceInRight');

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order`  (
  `oid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `did` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `uid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`oid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_order
-- ----------------------------
INSERT INTO `t_order` VALUES ('2646C84869504B95B44E1BADE9D8B8A7', '6B830973D7EA4A5FB8F624E75F735DDA', '09177DC9FBD347538EABC88E7FA43A46', '0');
INSERT INTO `t_order` VALUES ('2981438250C542B2B4515DAA2B1C0CCB', '081982E787814B28B5CD58689EFB8FCD', '09177DC9FBD347538EABC88E7FA43A46', '0');
INSERT INTO `t_order` VALUES ('30F790C420D24BF482F81A70DDA72B81', '4070C042FE7743E59756E8F5A308415A', '09177DC9FBD347538EABC88E7FA43A46', '0');
INSERT INTO `t_order` VALUES ('3129D3FCE43F4DF8A1AAD9C6ADA495AB', '35056263CA604FAF9EB69F479B11AA22', '09177DC9FBD347538EABC88E7FA43A46', '0');
INSERT INTO `t_order` VALUES ('4DEBDBA535CF49C7B41E69AFEC2DB663', '4070C042FE7743E59756E8F5A308415A', '09177DC9FBD347538EABC88E7FA43A46', '0');
INSERT INTO `t_order` VALUES ('61A5ADE5391742958FCF0520D9086488', '0CD0D1D047CA40919EA5EB66385C05A0', '09177DC9FBD347538EABC88E7FA43A46', '0');
INSERT INTO `t_order` VALUES ('6225A43B303F4E2A89932C7417495E4E', '3D072C79B57B4065AE3F14D8065E689C', '7C11C5E4B54D479BA781BD9CAA10F09C', '0');
INSERT INTO `t_order` VALUES ('6FFFC768B2114DFDBE184A8592E1775D', '35056263CA604FAF9EB69F479B11AA22', '5456456465465', '0');
INSERT INTO `t_order` VALUES ('83E448060CD345D89AECEE5775CAA5E0', '35056263CA604FAF9EB69F479B11AA22', '09177DC9FBD347538EABC88E7FA43A46', '0');
INSERT INTO `t_order` VALUES ('86B8A8B0F9554C6FAD7AFA9A8923846B', '91693A4C72634CB780F34F5DE7D1340F', '7C11C5E4B54D479BA781BD9CAA10F09C', '0');
INSERT INTO `t_order` VALUES ('9606F1E702C0428D96780B0673FBD3E8', '344E1C075E14418E9B760EF72725BD00', '09177DC9FBD347538EABC88E7FA43A46', '0');
INSERT INTO `t_order` VALUES ('9F064355D07F4EE7BDC29C6025CAD340', 'D0A5AC8840D84AD29DE2597BDC4E482D', '7C11C5E4B54D479BA781BD9CAA10F09C', '0');
INSERT INTO `t_order` VALUES ('A634CE95E2FA43918531B57F206ABDBA', '4070C042FE7743E59756E8F5A308415A', '09177DC9FBD347538EABC88E7FA43A46', '0');
INSERT INTO `t_order` VALUES ('A8EC057C035349498728AA3E19F8C0CF', '35056263CA604FAF9EB69F479B11AA22', '7C11C5E4B54D479BA781BD9CAA10F09C', '0');
INSERT INTO `t_order` VALUES ('C00A342E8F4A4ED395301D3C9071512C', '1B0F7671D6294882AB1D113987B87042', '7C11C5E4B54D479BA781BD9CAA10F09C', '0');
INSERT INTO `t_order` VALUES ('E6101E7534184D78B01CAB691BE34D82', '081982E787814B28B5CD58689EFB8FCD', '09177DC9FBD347538EABC88E7FA43A46', '0');
INSERT INTO `t_order` VALUES ('F2881E7F31574AE29AB4D4A433B029F5', '69C304417E3B486C9C55ADA8A63707BA', NULL, '0');
INSERT INTO `t_order` VALUES ('F3EF281F0C194998990F0901424964EF', '1B0F7671D6294882AB1D113987B87042', '99F0AA6EE2B540108E5DED3E74612AAE', '0');
INSERT INTO `t_order` VALUES ('F53C1BF7F15B453B84BFA359E7C58A32', 'C5BEF765BBD241C5A55624B1809F2DD0', '99F0AA6EE2B540108E5DED3E74612AAE', '0');
INSERT INTO `t_order` VALUES ('FA679298EFA746038C7238BAD7040A7A', 'AFEA07A292F4425E99B9831A6F961A8D', '09177DC9FBD347538EABC88E7FA43A46', '0');
INSERT INTO `t_order` VALUES ('FB3E6DB6652C42548C54F436E7714EC4', '4070C042FE7743E59756E8F5A308415A', '7C11C5E4B54D479BA781BD9CAA10F09C', '0');
INSERT INTO `t_order` VALUES ('FC886B5EE36D4DEBABAC6677B2F7F0CB', '35056263CA604FAF9EB69F479B11AA22', '7C11C5E4B54D479BA781BD9CAA10F09C', '0');
INSERT INTO `t_order` VALUES ('FCEA2F5B6EAE4362B6DC6010AAF43D9A', '4070C042FE7743E59756E8F5A308415A', '09177DC9FBD347538EABC88E7FA43A46', '0');
INSERT INTO `t_order` VALUES ('FF58028F69E64F9F8E1F9BBB45C75394', 'C129C9AFB6B04D99B11E16650717E8EC', '7C11C5E4B54D479BA781BD9CAA10F09C', '0');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `uid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `uname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `upassword` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('09177DC9FBD347538EABC88E7FA43A46', '查欢', '666');
INSERT INTO `t_user` VALUES ('7C11C5E4B54D479BA781BD9CAA10F09C', '韩春阳', '123');
INSERT INTO `t_user` VALUES ('99F0AA6EE2B540108E5DED3E74612AAE', '啊啊啊', '123456');

SET FOREIGN_KEY_CHECKS = 1;
