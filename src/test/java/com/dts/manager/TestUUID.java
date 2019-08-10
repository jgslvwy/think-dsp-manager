package com.dts.manager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class TestUUID {

        public static void readTxt(String filePath) {

            try {
                File file = new File(filePath);
                if(file.isFile() && file.exists()) {
                    InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "utf-8");
                    BufferedReader br = new BufferedReader(isr);
                    String lineTxt = null;
                    int i = 100;
                    while ((lineTxt = br.readLine()) != null) {
                        String[] strings = lineTxt.split(",");
                        String str = strings[0];
                        String str1 = strings[1];
                        String str2 = strings[2];
                        i++;
                        String sy = "INSERT INTO `T_CRM_CSRAGENT_TASK` (`ID`,`PK_TASK`,`FK_CUSTOMER`,`FK_CORP`,`FK_PERSON_CSRAGENT`," +
                            "`PERSON_NAME_CSRAGENT`,`IMPORTANCE_FLAG`,`RETURN_VISIT_FLAG`,`DISTRIBUTION_TIME`,`REMARKS`,`CREATE_TIME`,`FK_USER_CREATE`," +
                            "`USER_NAME_CREATE`,`UPDATE_TIME`,`FK_USER_UPDATE`,`USER_NAME_UPDATE`)VALUES (" +
                            i + "," +
                            "REPLACE (UUID(), \"-\", \"\"), CASE WHEN ( SELECT PK_CUSTOMER FROM T_CRM_CALL_CUSTOMER WHERE CUSTOMER_NAME = "
                            + "'" + str + "'" + ") IS NULL THEN 'ERROR'  ELSE  (SELECT PK_CUSTOMER FROM T_CRM_CALL_CUSTOMER WHERE CUSTOMER_NAME = "
                            + "'" + str + "'" + ") END, NULL," +
                            "'"+str1+"'" +
                            "," +
                            "'"+str2+"'" +
                            ",'0','0',NOW(),NULL,NOW(),'admin','admin',NOW(),'admin','admin'); ";

                        System.out.println(sy);
                    }
                    br.close();
                } else {
                    System.out.println("文件不存在!");
                }
            } catch (Exception e) {
                System.out.println("文件读取错误!");
            }

        }


        public static void main(String[] args) {
            String filePath = "C:\\Users\\A\\Desktop\\CRMci修改内容\\qqq.txt";
            readTxt(filePath);

            readStatus();
        }



        public static void readStatus(){
            String crm_man_str = "CRM管理岗";
            String crm_man_str1 = "CRM营销坐席岗";
            String crm_man_str2 = "CRM外呼客服岗";

            String crm_man_st_post ="郭旸,邢莲莲,马雪,郭笑凯,孙怡佳,李萌,杨超,冯婷婷,王芸,焦思行,马俊杰,王乃乐,程艾红,杨小侠";
            String crm_man_st_post1 ="辛星,刘焱";
            String crm_man_st_post2 ="何月花,李悦,邹南芳,邢岩,韩磊,林思含,武萌萌,唐一迪,刘晓峰,吴维娜,李圆圆,石建平,徐小建,陈倩,王婷,张海慧,乌丹丹,谭启凤,曹芳芳,马秀雅,宋雅俐," +
                "逄慕晗," +
                "陈丽娜," +
                "关蕊," +
                "朱葛," +
                "马天才," +
                "邢丽新," +
                "刘佰慧," +
                "张强," +
                "魏欣," +
                "孔令怡," +
                "李逢春," +
                "张如利," +
                "王臣," +
                "杨扬," +
                "徐嘉玮," +
                "王尚敏," +
                "黄蕾," +
                "梁飒," +
                "李彬," +
                "陈静宜," +
                "高苗苗," +
                "黄盛兴," +
                "何偌菲," +
                "李彤," +
                "肖雪婷," +
                "任姜齐," +
                "王伟伟," +
                "张骜峰," +
                "姬晨," +
                "王亚忱," +
                "刘国新," +
                "穆锦云," +
                "郑双," +
                "王乃乐," +
                "王芸," +
                "马俊杰," +
                "焦思行";
            String[] crm_manam = crm_man_st_post.split(",");
            for(int i = 0;i<crm_manam.length;i++){
                System.out.println("-------------------CRM管理岗---------------------------------");
                String str = "INSERT INTO T_ORG_STAFF_POST (SEQUENCE_NO, FK_STAFF, STAFF_NAME, FK_POST, POST_NAME, EFFECT_TIME, EXPIRY_TIME, OPERATION_CODE, CREATE_TIME, FK_USER_CREATE, USER_NAME_CREATE, UPDATE_TIME, FK_USER_UPDATE, USER_NAME_UPDATE, DELETE_TIME, \n" +
                    "FK_USER_DELETE, USER_NAME_DELETE, DATA_VERSION, IS_DATA_LOCKED, FK_ORIGINAL) VALUES (SEQ_T_ORG_STAFF_POST.nextval, (select t.pk_staff from T_ORG_STAFF t where t.staff_name = " +
                    "'" +
                    crm_manam[i] +
                    "'),\n" +
                    "'" +
                    crm_manam[i] +
                    "', (select t.pk_post from t_org_post t where t.post_name = '" +
                    crm_man_str +
                    "'), '" +
                    crm_man_str +
                    "', TO_DATE('2019-07-08 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), \n" +
                    "TO_DATE('2030-09-13 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'CREATE', TO_DATE('2019-07-08 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), '0', \n" +
                    "'建信融通系统管理员', TO_DATE('2019-07-08 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), '0', '建信融通系统管理员', null, null, null, '0', '0', null);";
                System.out.println(str);
                System.out.println();

            }

            String[] crm_manam1 = crm_man_st_post1.split(",");
            for(int i = 0;i<crm_manam1.length;i++){
                System.out.println("-------------------CRM营销坐席岗---------------------------------");
                String str = "INSERT INTO T_ORG_STAFF_POST (SEQUENCE_NO, FK_STAFF, STAFF_NAME, FK_POST, POST_NAME, EFFECT_TIME, EXPIRY_TIME, " +
                    "OPERATION_CODE, CREATE_TIME, FK_USER_CREATE, USER_NAME_CREATE," +
                    " UPDATE_TIME, FK_USER_UPDATE, USER_NAME_UPDATE, DELETE_TIME, \n" +
                    "FK_USER_DELETE, USER_NAME_DELETE, DATA_VERSION, IS_DATA_LOCKED, FK_ORIGINAL) VALUES (SEQ_T_ORG_STAFF_POST.nextval, (select t.pk_staff from T_ORG_STAFF t where t.staff_name = " +
                    "'" +
                    crm_manam1[i] +
                    "'),\n" +
                    "'" +
                    crm_manam1[i] +
                    "', (select t.pk_post from t_org_post t where t.post_name = '" +
                    crm_man_str1 +
                    "'), '" +
                    crm_man_str1 +
                    "', TO_DATE('2019-07-08 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), \n" +
                    "TO_DATE('2030-09-13 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'CREATE', TO_DATE('2019-07-08 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), '0', \n" +
                    "'建信融通系统管理员', TO_DATE('2019-07-08 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), '0', '建信融通系统管理员', null, null, null, '0', '0', null);";
                System.out.println(str);
            }

            String[] crm_manam2 = crm_man_st_post2.split(",");
            for(int i = 0;i<crm_manam2.length;i++){
                System.out.println("-------------------CRM外呼客服岗---------------------------------");

                String str = "INSERT INTO T_ORG_STAFF_POST (SEQUENCE_NO, FK_STAFF, STAFF_NAME, FK_POST, POST_NAME, EFFECT_TIME, EXPIRY_TIME, OPERATION_CODE, CREATE_TIME, FK_USER_CREATE, USER_NAME_CREATE, UPDATE_TIME, FK_USER_UPDATE, USER_NAME_UPDATE, DELETE_TIME, \n" +
                    "FK_USER_DELETE, USER_NAME_DELETE, DATA_VERSION, IS_DATA_LOCKED, FK_ORIGINAL) VALUES (SEQ_T_ORG_STAFF_POST.nextval, (select t.pk_staff from T_ORG_STAFF t where t.staff_name = " +
                    "'" +
                    crm_manam2[i] +
                    "'),\n" +
                    "'" +
                    crm_manam2[i] +
                    "', (select t.pk_post from t_org_post t where t.post_name = '" +
                    crm_man_str2 +
                    "'), '" +
                    crm_man_str2 +
                    "', TO_DATE('2019-07-08 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), \n" +
                    "TO_DATE('2030-09-13 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'CREATE', TO_DATE('2019-07-08 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), '0', \n" +
                    "'建信融通系统管理员', TO_DATE('2019-07-08 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), '0', '建信融通系统管理员', null, null, null, '0', '0', null);";
                System.out.println(str);
            }







        }

    }
