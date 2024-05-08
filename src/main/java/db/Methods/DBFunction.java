package db.Methods;

import com.saf.framework.CommonLib;
import io.qameta.allure.Allure;
import io.qameta.allure.model.Status;

import java.sql.*;


public class DBFunction {

    //get connection for requested DB
    private static Connection con;
    CommonLib commonLib = new CommonLib();


    public static Connection getConnection(String connectionStr, String username, String password) {
        Connection con = null;
        try {
            // step1 load the driver class
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // step2 create the connection object
            con = DriverManager.getConnection(connectionStr, username, password);
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

        return con;
    }

    public ResultSet executeSelectQuery(Connection con, String query) {
        ResultSet rs = null;
        Statement stmt = null;
        try {

            stmt = con.createStatement();
            rs = stmt.executeQuery(query);

        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        } catch (Exception ex) {
            // TODO: handle exception
            ex.printStackTrace();
        }
        return rs;
    }


    public static void updateSelectQuery(Connection con, String query) {
        int rs = 0;
        Statement stmt = null;
        try {

            stmt = con.createStatement();
            rs = stmt.executeUpdate(query);
            // rs = stmt.executeUpdate();

        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        } catch (Exception ex) {
            // TODO: handle exception
            ex.printStackTrace();
        }

    }

    public void close(Connection con) {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            System.out.println("Couldn't close connection: " + e.getMessage());
        }
    }

    public String selectFaturasizGSMNo() throws Exception {
        String gsmPostfix = "";
        String gsmNo = "";
        con = getConnection("jdbc:oracle:thin:@izmcon11dt-scan:1522/CFICCB", "TESTUSER", "TESTUSER");

        String queryFaturasizGsmNo = "SELECT GSM_NO FROM ccb.ccb_subscriber WHERE status='A' and gsm_no not in (select gsm_no from ccb.ccb_manual_forward_mig) and customer_id IN (SELECT customer_id FROM ccb.ccb_customer WHERE TYPE ='S' and citizenship='TR') AND (gsm_no, start_date) IN (SELECT gsm_no, start_date FROM ccb.ccb_subscriber_package sp WHERE package_id like ('PPHCN01%') AND sw_date IN (SELECT MAX (sw_date) FROM ccb.ccb_subscriber_package WHERE gsm_no =sp.gsm_No AND start_date =sp.start_Date) and gsm_no not in (SELECT gsm_no FROM ccb.ccb_provision_log where gsm_no=sp.gsm_no and TRANSACTION_CODE='500' and log_status='E')) order by cr_date DESC";
        ResultSet rs = executeSelectQuery(con, queryFaturasizGsmNo);
        try {
            while (rs.next()) {
                gsmNo = rs.getString(1);
                gsmPostfix = gsmNo.substring(3);
//					ReportManager.ReportEvent(TestStatus.PASS, "Execute DB Select Query: " + queryPostfixGsmNo  , "GSM Postfix select value: " + gsmPostfix +" - Successfull".toUpperCase());
                Allure.step("Execute DB Select Query: " + queryFaturasizGsmNo + "GSM No select value: " + gsmNo + " - Successfull", Status.PASSED);
                break;
            }
            rs.close();
            close(con);
            return gsmNo;

        } catch (SQLException e) {
            Allure.step("DB Select Error:" + "A fail occured on query." + e.getMessage(), Status.FAILED);

            e.printStackTrace();
            return null;
        }
    }


    public String selectCustomerID() throws Exception {
        String customerID = "";
        con = getConnection("jdbc:oracle:thin:@izmcon5dt-scan:1521/SRV_TBCEBTRL", "TESTUSER", "TESTUSER");

        String queryCustomerID = "select TC_ID from PROVISIONING_MGNT.MERNIS_CUSTOMER_INFO WHERE tc_id LIKE '%44' AND DATE_OF_BIRTH_YEAR ='1992' order by dbms_random.random";
        ResultSet rs = executeSelectQuery(con, queryCustomerID);
        try {
            while (rs.next()) {
                customerID = rs.getString(1);
                Allure.step("Execute DB Select Query: " + queryCustomerID + "Customer ID select value: " + customerID + " - Successfull", Status.PASSED);
                break;
            }
            rs.close();
            close(con);
            return customerID;

        } catch (SQLException e) {
            Allure.step("DB Select Error:" + "A fail occured on query." + e.getMessage(), Status.FAILED);

            e.printStackTrace();
            return null;
        }
    }

    public String selectFatherName() throws Exception {
        String fatherName = "";
        con = getConnection("jdbc:oracle:thin:@izmcon5dt-scan:1521/SRV_TBCEBTRL", "TESTUSER", "TESTUSER");

        String queryFatherName = "select FATHER_NAME from PROVISIONING_MGNT.MERNIS_CUSTOMER_INFO WHERE tc_id LIKE '%44' AND DATE_OF_BIRTH_YEAR ='1992' AND FATHER_NAME ='PET' order by dbms_random.random";
        ResultSet rs = executeSelectQuery(con, queryFatherName);
        try {
            while (rs.next()) {
                fatherName = rs.getString(1);
                Allure.step("Execute DB Select Query: " + queryFatherName + " Father Name select value: " + fatherName + " - Successfull", Status.PASSED);
                break;
            }
            rs.close();
            close(con);
            return fatherName;

        } catch (SQLException e) {
            Allure.step("DB Select Error:" + "A fail occured on query." + e.getMessage(), Status.FAILED);

            e.printStackTrace();
            return null;
        }
    }


    public String selectMotherName() throws Exception {
        String motherName = "";
        con = getConnection("jdbc:oracle:thin:@izmcon5dt-scan:1521/SRV_TBCEBTRL", "TESTUSER", "TESTUSER");

        String queryMotherName = "select MOTHER_NAME from PROVISIONING_MGNT.MERNIS_CUSTOMER_INFO WHERE tc_id LIKE '%44' AND DATE_OF_BIRTH_YEAR ='1992' AND FATHER_NAME ='PET' order by dbms_random.random";
        ResultSet rs = executeSelectQuery(con, queryMotherName);
        try {
            while (rs.next()) {
                motherName = rs.getString(1);
                Allure.step("Execute DB Select Query: " + queryMotherName + " Father Name select value: " + motherName + " - Successfull", Status.PASSED);
                break;
            }
            rs.close();
            close(con);
            return motherName;

        } catch (SQLException e) {
            Allure.step("DB Select Error:" + "A fail occured on query." + e.getMessage(), Status.FAILED);

            e.printStackTrace();
            return null;
        }
    }

    public String selectDocumentNo() throws Exception {
        String documentNo = "";
        con = getConnection("jdbc:oracle:thin:@izmcon5dt-scan:1521/SRV_TBCEBTRL", "TESTUSER", "TESTUSER");

        String queryDocumentNo = "select DOCUMENT_NO from PROVISIONING_MGNT.MERNIS_CUSTOMER_INFO WHERE tc_id LIKE '%44' AND DATE_OF_BIRTH_YEAR ='1992' AND DOCUMENT_NO LIKE 'A010%' order by dbms_random.random";
        ResultSet rs = executeSelectQuery(con, queryDocumentNo);
        try {
            while (rs.next()) {
                documentNo = rs.getString(1);
                Allure.step("Execute DB Select Query: " + queryDocumentNo + "Document No select value: " + documentNo + " - Successfull", Status.PASSED);
                break;
            }
            rs.close();
            close(con);
            return documentNo;

        } catch (SQLException e) {
            Allure.step("DB Select Error:" + "A fail occured on query." + e.getMessage(), Status.FAILED);

            e.printStackTrace();
            return null;
        }
    }

    public String selectName() throws Exception {
        String nameNo = "";
        con = getConnection("jdbc:oracle:thin:@izmcon5dt-scan:1521/SRV_TBCEBTRL", "TESTUSER", "TESTUSER");

        String queryNameNo = "select NAME from PROVISIONING_MGNT.MERNIS_CUSTOMER_INFO WHERE tc_id LIKE '%44' AND DATE_OF_BIRTH_YEAR ='1992' AND DOCUMENT_NO LIKE 'A010%' order by dbms_random.random";
        ResultSet rs = executeSelectQuery(con, queryNameNo);
        try {
            while (rs.next()) {
                nameNo = rs.getString(1);
                Allure.step("Execute DB Select Query: " + queryNameNo + "Document No select value: " + nameNo + " - Successfull", Status.PASSED);
                break;
            }
            rs.close();
            close(con);
            return nameNo;

        } catch (SQLException e) {
            Allure.step("DB Select Error:" + "A fail occured on query." + e.getMessage(), Status.FAILED);

            e.printStackTrace();
            return null;
        }
    }

    //--Benim yazdıklarım.
//--> Burada gsm no yu alıyoruz ve iç faturasız tüm iç akışlarda kullanabiliriz. => PASS
    public String selectGSMNoPrepaidInternal() throws Exception {
        //doğru sorgu buna göre işlem alabilirsin db name i önemli sadece
        String customerID = "";
        con = getConnection("jdbc:oracle:thin:@izmcon11dt-scan:1522/CFICCB", "TESTUSER", "TESTUSER");

        String queryCustomerID = "SELECT * \n" +
                "  FROM ccb.ccb_subscriber \n" +
                " WHERE status='A' and gsm_no not in (select gsm_no from ccb.ccb_manual_forward_mig) and\n" +
                "customer_id IN (SELECT customer_id \n" +
                "                         FROM ccb.ccb_customer \n" +
                "                        WHERE TYPE = 'S' and citizenship='TR') \n" +
                "       AND (gsm_no, start_date) IN \n" +
                "              (SELECT gsm_no, start_date \n" +
                "                 FROM ccb.ccb_subscriber_package sp  \n" +
                "                WHERE package_id like ('PPHCN01%')                      ---- P2P , DEVİR VE FATURASIZ İÇ AKIŞ İÇİN KULLANILABİLECEK GSM NO ‘ LAR   DB BİLGİLERİ :  // CFICCB  Hostname : izmcon11dt-scan.vodafone.local  // Port : 1522 // Service Name : CFICCB \n" +
                "                      AND sw_date IN \n" +
                "                             (SELECT MAX (sw_date) \n" +
                "                                FROM ccb.ccb_subscriber_package \n" +
                "                               WHERE gsm_no = sp.gsm_No \n" +
                "                                     AND start_date = sp.start_Date)\n" +
                "                                     and gsm_no not in (SELECT gsm_no FROM ccb.ccb_provision_log where gsm_no=sp.gsm_no and TRANSACTION_CODE='500' and log_status='E'))\n" +
                "                                     and gsm_no like '543107%'\n" +
                "                                                                                                                                                           \n" +
                "                                     order by dbms_random.normal, start_date desc";
        ResultSet rs = executeSelectQuery(con, queryCustomerID);
        try {
            while (rs.next()) {
                customerID = rs.getString(1);
                Allure.step("Execute DB Select Query: " + queryCustomerID + "Customer ID select value: " + customerID + " - Successfull", Status.PASSED);
                break;
            }
            rs.close();
            close(con);
            return customerID;

        } catch (SQLException e) {
            Allure.step("DB Select Error:" + "A fail occured on query." + e.getMessage(), Status.FAILED);

            e.printStackTrace();
            return null;
        }
    }


    public String selectGSMForFaturasizIc() throws Exception {
        String customerID = "";
        con = getConnection("jdbc:oracle:thin:@izmcon11dt-scan:1522/CFICCB", "TESTUSER", "TESTUSER");

        String queryCustomerID = "SELECT *\n" +
                "  FROM ccb.ccb_subscriber\n" +
                "WHERE status='A' and gsm_no not in (select gsm_no from ccb.ccb_activation_channel_log where status='0') and\n" +
                "customer_id IN (SELECT customer_id\n" +
                "                         FROM ccb.ccb_customer\n" +
                "                        WHERE TYPE = 'S' and citizenship='TR')\n" +
                "       AND (gsm_no, start_date) IN\n" +
                "              (SELECT gsm_no, start_date\n" +
                "                 FROM ccb.ccb_subscriber_package sp    --Gsm no for faturasız\n" +
                "                WHERE package_id  like ('PPH%')\n" +
                "                      AND sw_date IN\n" +
                "                             (SELECT MAX (sw_date)\n" +
                "                                FROM ccb.ccb_subscriber_package\n" +
                "                               WHERE gsm_no = sp.gsm_No\n" +
                "                                     AND start_date = sp.start_Date)\n" +
                "                                     and gsm_no not in (SELECT gsm_no FROM ccb.ccb_provision_log where gsm_no=sp.gsm_no and TRANSACTION_CODE='500' and log_status='E'))\n" +
                "                                     order by dbms_random.normal";
        ResultSet rs = executeSelectQuery(con, queryCustomerID);
        try {
            while (rs.next()) {
                customerID = rs.getString(1);
                Allure.step("Execute DB Select Query: " + queryCustomerID + "Customer ID select value: " + customerID + " - Successfull", Status.PASSED);
                break;
            }
            rs.close();
            close(con);
            return customerID;

        } catch (SQLException e) {
            Allure.step("DB Select Error:" + "A fail occured on query." + e.getMessage(), Status.FAILED);

            e.printStackTrace();
            return null;
        }
    }

    public String selectGSMForFaturaliIc() throws Exception {
        String customerID = "";
        con = getConnection("jdbc:oracle:thin:@//172.31.70.173:1526/SBLCBUST", "TESTUSER", "TESTUSER");

        String queryCustomerID = "select * FROM (select main.serial_num gsm,con.soc_securıty_num TCKN,acc.ou_num Customer_Code,con.FST_NAME FirstName, con. LAST_NAME LastName, con. X_ATTRIB_41 FatherName,con.X_ATTRIB_06 Mother_Name,  con.BIRTH_DT BirthDate, con.SEX_MF Gender,con.X_ATTRIB_39 BirthPlace,ass.status_cd STATU,con.x_vf_bg_error_code bg_list_error_code, con.x_vf_bg_error_description bg_error_description\n" +
                "from siebel.s_prod_int prod, siebel.s_asset ass, siebel.s_asset main, siebel.s_org_ext acc,siebel.s_order so, siebel.S_CONTACT_X conx, SIEBEL.S_CONTACT con\n" +
                "Where Ass.Prod_Id = Prod.Row_Id\n" +
                "AND prod.part_num = '70000'     \n" +
                "AND ass.orig_order_id = so.row_id\n" +
                "AND ass.root_asset_id =main.row_id\n" +
                "AND acc.par_row_id =main.owner_accnt_id\n" +
                "AND conx.row_id=acc.PR_CON_ID\n" +
                "AND con.row_id=acc.PR_CON_ID\n" +
                "and ass.status_cd = 'Active'\n" +
                "and con.BIRTH_DT< to_date ('01-01-2003', 'DD-MM-YYYY')\n" +
                "and conx.X_VF_CITIZENSHIP = 'Türkiye'\n" +
                "and con.x_vf_bg_error_code='VFTK-BLINQ-0000'\n" +
                "and con.x_vf_bg_error_description='Not in lists'\n" +
                "--and acc.name LIKE 'SOT%'\n" +
                "and con.X_ATTRIB_08='Y'\n" +
                "and so.STATUS_CD <> 'Open'\n" +
                "Order By dbms_random.normal)";
        ResultSet rs = executeSelectQuery(con, queryCustomerID);
        try {
            while (rs.next()) {
                customerID = rs.getString(1);
                Allure.step("Execute DB Select Query: " + queryCustomerID + "Customer ID select value: " + customerID + " - Successfull", Status.PASSED);
                break;
            }
            rs.close();
            close(con);
            return customerID;

        } catch (SQLException e) {
            Allure.step("DB Select Error:" + "A fail occured on query." + e.getMessage(), Status.FAILED);

            e.printStackTrace();
            return null;
        }
    }

    public String prepaidSimkartIcci() throws Exception {
        String nameNo = "";
        con = getConnection("jdbc:oracle:thin:@izmcon11dt-scan:1522/CFICCB", "TESTUSER", "TESTUSER");

        String queryNameNo = "select icci from ccb.ccb_icci_sim_pool ic where card_type = 'NO'\n" +
                "  and icci_Status_code = 'BA'\n" +
                "  and length(icci) = 18 \n" +
                "  and usage_reason_code = 'AK'\n" +
                "  and campaign_Code not like  'MNP%'\n" +
                "  AND ICCI like '899002%'\n" +
                "  and lock_ind = 'H'\n" +
                "  and campaign_code='DEF_MC'\n" +
                "  and exists ( select * from ccb.ccb_imsi_pool where gsm_no is null\n" +
                "  and start_date is null\n" +
                "  and imsi_status_code = 'PS' and brand_code = 'MC' and  POST_IND ='H' and ic.icci = icci)--POST_IND='H'  and\n" +
                "  and not exists (select * from CCB.CCB_SIEBEL_IMSI_POOL_EXT where ic.icci = icci)                                    -- FATURASIZ HATLAR--TR HATLAR\n" +
                "  and rownum<5\n" +
                "  order by dbms_random.normal";
        ResultSet rs = executeSelectQuery(con, queryNameNo);
        try {
            while (rs.next()) {
                nameNo = rs.getString(1);
                Allure.step("Execute DB Select Query: " + queryNameNo + "Document No select value: " + nameNo + " - Successfull", Status.PASSED);
                break;
            }
            rs.close();
            close(con);
            return nameNo;

        } catch (SQLException e) {
            Allure.step("DB Select Error:" + "A fail occured on query." + e.getMessage(), Status.FAILED);

            e.printStackTrace();
            return null;
        }
    }

    public String prepaidTCId() throws Exception {
        String nameNo = "";
        con = getConnection("jdbc:oracle:thin:@izmcon5dt-scan:1521/SRV_TBCEBTRL", "TESTUSER", "TESTUSER");

        String queryTCNo = "SELECT * FROM PROVISIONING_MGNT.MERNIS_CUSTOMER_INFO where name='SOTO'\n" +
                "AND surname='SOTO'\n" +
                "AND date_of_bırth_year='1999'\n" +
                "AND rownum < 11\n" +
                "order by dbms_random.normal";
        ResultSet rs = executeSelectQuery(con, queryTCNo);
        try {
            while (rs.next()) {
                nameNo = rs.getString(1);
                Allure.step("Execute DB Select Query: " + queryTCNo + "Document No select value: " + nameNo + " - Successfull", Status.PASSED);
                break;
            }
            rs.close();
            close(con);
            return nameNo;

        } catch (SQLException e) {
            Allure.step("DB Select Error:" + "A fail occured on query." + e.getMessage(), Status.FAILED);

            e.printStackTrace();
            return null;
        }
    }


    public String externalPostpaidTcId() throws Exception {
        String nameNo = "";
        con = getConnection("jdbc:oracle:thin:@izmcon5dt-scan:1521/SRV_TBCEBTRL", "TESTUSER", "TESTUSER");

        String queryTCNo = "Select TC_ID from PROVISIONING_MGNT.MERNIS_CUSTOMER_INFO WHERE tc_id LIKE '%44' AND DATE_OF_BIRTH_YEAR ='1999' order by dbms_random.random";
        ResultSet rs = executeSelectQuery(con, queryTCNo);
        try {
            while (rs.next()) {
                nameNo = rs.getString(1);
                Allure.step("Execute DB Select Query: " + queryTCNo + "Document No select value: " + nameNo + " - Successfull", Status.PASSED);
                break;
            }
            rs.close();
            close(con);
            return nameNo;

        } catch (SQLException e) {
            Allure.step("DB Select Error:" + "A fail occured on query." + e.getMessage(), Status.FAILED);

            e.printStackTrace();
            return null;
        }
    }


    public String externalPostpaidMNPIcci() throws Exception {
        String nameNo = "";
        con = getConnection("jdbc:oracle:thin:@izmcon11dt-scan:1522/CFICCB", "TESTUSER", "TESTUSER");

        String queryTCNo = "SELECT icci FROM ccb.ccb_icci_sim_pool ic \n" +
                "WHERE card_type='NO' AND icci_Status_code='BA' \n" +
                "AND length(icci)=18 AND usage_reason_code='AK' \n" +
                "AND campaign_Code like 'MNP%' AND lock_ind='H' \n" +
                "AND icci LIKE '899002%'                      --- MNP ICCI  faturalı \n" +
                "--AND OWNER_SHOP_CODE='S900005' \n" +
                "AND exists (SELECT * FROM ccb.ccb_imsi_pool WHERE gsm_no is null AND start_date is null AND imsi_status_code='PS' AND brand_code='PO' AND ic.icci=icci) \n" +
                "AND NOT exists (SELECT * FROM CCB.CCB_SIEBEL_IMSI_POOL_EXT WHERE ic.icci=icci) \n" +
                "Order by dbms_random.normal";
        ResultSet rs = executeSelectQuery(con, queryTCNo);
        try {
            while (rs.next()) {
                nameNo = rs.getString(1);
                Allure.step("Execute DB Select Query: " + queryTCNo + "Document No select value: " + nameNo + " - Successfull", Status.PASSED);
                break;
            }
            rs.close();
            close(con);
            return nameNo;

        } catch (SQLException e) {
            Allure.step("DB Select Error:" + "A fail occured on query." + e.getMessage(), Status.FAILED);

            e.printStackTrace();
            return null;
        }
    }

    //Tüm mnp iccilerde kullanabilirsin
    public String mnpIcci() throws Exception {
        String nameNo = "";
        con = getConnection("jdbc:oracle:thin:@izmcon11dt-scan:1522/CFICCB", "TESTUSER", "TESTUSER");

        String queryTCNo = "SELECT icci FROM ccb.ccb_icci_sim_pool ic \n" +
                "WHERE card_type='NO' AND icci_Status_code='BA' \n" +
                "AND length(icci)=18 AND usage_reason_code='AK' \n" +
                "AND campaign_Code like 'MNP%' AND lock_ind='H' \n" +
                "AND icci LIKE '899002%'                      --- MNP ICCI \n" +
                "--AND OWNER_SHOP_CODE='S900005' \n" +
                "AND exists (SELECT * FROM ccb.ccb_imsi_pool WHERE gsm_no is null AND start_date is null AND imsi_status_code='PS' AND brand_code='PO' AND ic.icci=icci) \n" +
                "AND NOT exists (SELECT * FROM CCB.CCB_SIEBEL_IMSI_POOL_EXT WHERE ic.icci=icci) \n" +
                "AND rownum<200 ";
        ResultSet rs = executeSelectQuery(con, queryTCNo);
        try {
            while (rs.next()) {
                nameNo = rs.getString(1);
                Allure.step("Execute DB Select Query: " + queryTCNo + "Document No select value: " + nameNo + " - Successfull", Status.PASSED);
                break;
            }
            rs.close();
            close(con);
            return nameNo;

        } catch (SQLException e) {
            Allure.step("DB Select Error:" + "A fail occured on query." + e.getMessage(), Status.FAILED);

            e.printStackTrace();
            return null;
        }
    }


    public String postpaidIcci() throws Exception {
        String nameNo = "";
        con = getConnection("jdbc:oracle:thin:@izmcon11dt-scan:1522/CFICCB", "TESTUSER", "TESTUSER");

        String queryTCNo = " SELECT icci FROM ccb.ccb_icci_sim_pool ic\n" +
                "WHERE card_type='NO' AND icci_Status_code='BA'\n" +
                "AND length(icci)=18 AND usage_reason_code='AK'  --- FATURALI HATLAR\n" +
                "AND campaign_Code NOT like 'MNP%' AND lock_ind='H'\n" +
                "AND icci LIKE '899002%' AND OWNER_SHOP_CODE='S900005'\n" +
                "AND exists (SELECT * FROM ccb.ccb_imsi_pool WHERE gsm_no is null AND start_date is null AND imsi_status_code='PS' AND brand_code='PO' AND ic.icci=icci)\n" +
                "AND NOT exists (SELECT * FROM CCB.CCB_SIEBEL_IMSI_POOL_EXT WHERE ic.icci=icci)\n" +
                "AND rownum<5\n" +
                "ORDER By dbms_random.normal";
        ResultSet rs = executeSelectQuery(con, queryTCNo);
        try {
            while (rs.next()) {
                nameNo = rs.getString(1);
                Allure.step("Execute DB Select Query: " + queryTCNo + "Document No select value: " + nameNo + " - Successfull", Status.PASSED);
                break;
            }
            rs.close();
            close(con);
            return nameNo;

        } catch (SQLException e) {
            Allure.step("DB Select Error:" + "A fail occured on query." + e.getMessage(), Status.FAILED);

            e.printStackTrace();
            return null;
        }
    }



    public String prepaidMNPIcci() throws Exception {
        String nameNo = "";
        con = getConnection("jdbc:oracle:thin:@izmcon11dt-scan:1522/CFICCB", "TESTUSER", "TESTUSER");

        String queryTCNo = "SELECT *\n" +
                "  FROM ccb.ccb_imsi_pool i\n" +
                "WHERE     gsm_no IS NULL\n" +
                "       AND imsi_status_code = 'PS'\n" +
                "       AND brand_code = 'MC'\n" +
                "       AND act_date IS NULL\n" +
                "       AND EXISTS\n" +
                "              (SELECT 1\n" +
                "                 FROM ccb.ccb_icci_sim_pool\n" +
                "                WHERE     icci = i.icci\n" +
                "                      AND length(icci) = 18 \n" +
                "                      AND card_type = 'NO'\n" +
                "                      AND icci_status_code = 'BA'        -- mnp icci faturasız\n" +
                "                      AND usage_reason_code = 'AK'\n" +
                "                      AND campaign_code= 'MNP000'\n" +
                "                      AND lock_ind = 'H')\n" +
                "                      AND rownum < 11\n" +
                "                      Order by dbms_random.normal";
        ResultSet rs = executeSelectQuery(con, queryTCNo);
        try {
            while (rs.next()) {
                nameNo = rs.getString(1);
                Allure.step("Execute DB Select Query: " + queryTCNo + "Document No select value: " + nameNo + " - Successfull", Status.PASSED);
                break;
            }
            rs.close();
            close(con);
            return nameNo;

        } catch (SQLException e) {
            Allure.step("DB Select Error:" + "A fail occured on query." + e.getMessage(), Status.FAILED);

            e.printStackTrace();
            return null;
        }
    }



    public String prepaidGSMNo() throws Exception {
        String nameNo = "";
        con = getConnection("jdbc:oracle:thin:@izmcon11dt-scan:1522/CFICCB", "TESTUSER", "TESTUSER");

        String queryTCNo = "SELECT * \n" +
                "  FROM ccb.ccb_subscriber \n" +
                " WHERE status='A' and gsm_no not in (select gsm_no from ccb.ccb_manual_forward_mig) and\n" +
                "customer_id IN (SELECT customer_id \n" +
                "                         FROM ccb.ccb_customer \n" +
                "                        WHERE TYPE = 'S' and citizenship='TR') \n" +
                "       AND (gsm_no, start_date) IN \n" +
                "              (SELECT gsm_no, start_date \n" +
                "                 FROM ccb.ccb_subscriber_package sp \n" +
                "                WHERE package_id like ('PP%')                           --- Faturasız Hatlarda kullanılan gsm no iç akış\n" +
                "                      AND sw_date IN \n" +
                "                             (SELECT MAX (sw_date) \n" +
                "                                FROM ccb.ccb_subscriber_package \n" +
                "                               WHERE gsm_no = sp.gsm_No \n" +
                "                                     AND start_date = sp.start_Date)\n" +
                "                                     and gsm_no not in (SELECT gsm_no FROM ccb.ccb_provision_log where gsm_no=sp.gsm_no and TRANSACTION_CODE='500' and log_status='E'))\n" +
                "                                     and rownum<5\n" +
                "                                     order by dbms_random.normal";
        ResultSet rs = executeSelectQuery(con, queryTCNo);
        try {
            while (rs.next()) {
                nameNo = rs.getString(1);
                Allure.step("Execute DB Select Query: " + queryTCNo + "Document No select value: " + nameNo + " - Successfull", Status.PASSED);
                break;
            }
            rs.close();
            close(con);
            return nameNo;

        } catch (SQLException e) {
            Allure.step("DB Select Error:" + "A fail occured on query." + e.getMessage(), Status.FAILED);

            e.printStackTrace();
            return null;
        }
    }


    public String prepaidIcciSimCardChange() throws Exception {
        String nameNo = "";
        con = getConnection("jdbc:oracle:thin:@izmcon11dt-scan:1522/CFICCB", "TESTUSER", "TESTUSER");

        String queryTCNo = "select * from ccb.ccb_icci_sim_pool \n" +
                " WHERE     USAGE_REASON_CODE = 'YD' \n" +
                "       AND icci IN (SELECT icci \n" +
                "                          FROM ccb.ccb_imsi_pool \n" +
                "                         WHERE gsm_no IS NULL \n" +
                "                         \n" +
                "                         and brand_code='MC' \n" +
                "                         and imsi_status_code='PS')\n" +
                "                          \n" +
                "       AND CARD_TYPE = 'NO' \n" +
                "       AND ICCI_STATUS_CODE = 'BA' \n" +
                "       AND LOCK_IND = 'H'\n" +
                "       AND icci LIKE '%89900281%'\n" +
                "       AND CAMPAIGN_CODE='DEF_MC'\n" +
                "      \n" +
                "       AND rownum <50\n" +
                "       ORDER BY dbms_random.normal";
        ResultSet rs = executeSelectQuery(con, queryTCNo);
        try {
            while (rs.next()) {
                nameNo = rs.getString(1);
                Allure.step("Execute DB Select Query: " + queryTCNo + "Document No select value: " + nameNo + " - Successfull", Status.PASSED);
                break;
            }
            rs.close();
            close(con);
            return nameNo;

        } catch (SQLException e) {
            Allure.step("DB Select Error:" + "A fail occured on query." + e.getMessage(), Status.FAILED);

            e.printStackTrace();
            return null;
        }
    }


    public String foreignerPrepaidIcci() throws Exception {
        String nameNo = "";
        con = getConnection("jdbc:oracle:thin:@izmcon11dt-scan:1522/CFICCB", "TESTUSER", "TESTUSER");

        String foreignPrepaidIcci = "  select icci from ccb.ccb_icci_sim_pool ic where card_type = 'NO'" +
                "  and icci_Status_code = 'BA'\n" +
                "  and length(icci) = 18 \n" +
                "  and usage_reason_code = 'AK'\n" +
                "  and campaign_Code not like  'MNP%'\n" +
                "  AND ICCI like '899002%'\n" +
                "  and lock_ind = 'H'\n" +
                "  and exists ( select * from ccb.ccb_imsi_pool where gsm_no is null" +
                "  and start_date is null\n" +
                "  and imsi_status_code = 'PS' and brand_code = 'MC' and  POST_IND ='H' and ic.icci = icci)--POST_IND='H'  and" +
                "  and not exists (select * from CCB.CCB_SIEBEL_IMSI_POOL_EXT where ic.icci = icci)                                    -- FATURASIZ HATLAR -- TBP0  -- YABANCI ICCI " +
                "  and rownum<50";
        ResultSet rs = executeSelectQuery(con, foreignPrepaidIcci);
        try {
            while (rs.next()) {
                nameNo = rs.getString(1);
                Allure.step("Execute DB Select Query: " + foreignPrepaidIcci + "Document No select value: " + nameNo + " - Successfull", Status.PASSED);
                break;
            }
            rs.close();
            close(con);
            return nameNo;

        } catch (SQLException e) {
            Allure.step("DB Select Error:" + "A fail occured on query." + e.getMessage(), Status.FAILED);

            e.printStackTrace();
            return null;
        }
    }


    public String tcIdForPrepaidAndPostpaid() throws Exception {
        String nameNo = "";
        con = getConnection("jdbc:oracle:thin:@izmcon5dt-scan:1521/SRV_TBCEBTRL", "TESTUSER", "TESTUSER");

        String foreignPrepaidIcci = "select * from PROVISIONING_MGNT.MERNIS_CUSTOMER_INFO WHERE name = 'SOTO' AND DATE_OF_BIRTH_YEAR ='1992' and rownum <100 order by dbms_random.random";
        ResultSet rs = executeSelectQuery(con, foreignPrepaidIcci);
        try {
            while (rs.next()) {
                nameNo = rs.getString(1);
                Allure.step("Execute DB Select Query: " + foreignPrepaidIcci + "Document No select value: " + nameNo + " - Successfull", Status.PASSED);
                break;
            }
            rs.close();
            close(con);
            return nameNo;

        } catch (SQLException e) {
            Allure.step("DB Select Error:" + "A fail occured on query." + e.getMessage(), Status.FAILED);

            e.printStackTrace();
            return null;
        }
    }

    //4.5g kontrol
    public String updateGSMStatus() throws Exception {
        String nameNo = "";
        con = getConnection("jdbc:oracle:thin:@izmcon3dt-scan:1521/SRV_TSTUNIDB", "TABLET", "Tablet1234*");

        String updateGsmStatus = "UPDATE TABLET.WHITELIST t SET t.STATUS = 0 WHERE MSISDN = '5490019137'";
        updateAndCommitGSMStatus();

        ResultSet rs = executeSelectQuery(con, updateGsmStatus);
        try {

            if (rs.next()) {
                nameNo = rs.getString(4);
                System.out.println("Gsm status: " + rs.getString(5));
                Allure.step("Execute DB Select Query: " + updateGsmStatus + "Document No select value: " + nameNo + " - Successfull", Status.PASSED);
                //break;
            }
            rs.close();
            close(con);
            return nameNo;

        } catch (SQLException e) {
            Allure.step("DB Select Error:" + "A fail occured on query." + e.getMessage(), Status.FAILED);

            e.printStackTrace();
            return null;
        }
    }

    public String updateAndCommitGSMStatus() throws Exception {
        String nameNo = "";
        con = getConnection("jdbc:oracle:thin:@izmcon3dt-scan:1521/SRV_TSTUNIDB", "TABLET", "Tablet1234*");

        String updateGsmStatus = "commit";


        ResultSet rs = executeSelectQuery(con, updateGsmStatus);
        try {

            if (rs.next()) {
//                nameNo = rs.getString(4);
//                System.out.println("Gsm status: " + rs.getString(5));
                Allure.step("Execute DB Select Query: " + updateGsmStatus + "Document No select value: " + nameNo + " - Successfull", Status.PASSED);
                //break;
            }
            rs.close();
            close(con);
            return nameNo;

        } catch (SQLException e) {
            Allure.step("DB Select Error:" + "A fail occured on query." + e.getMessage(), Status.FAILED);

            e.printStackTrace();
            return null;
        }
    }

//4.5g kontrol

    public String changeGSMStatus() {
        String nameNo = "";
        con = getConnection("jdbc:oracle:thin:@izmcon3dt-scan:1521/SRV_TSTUNIDB", "TABLET", "Tablet1234*");


        String changeGSMStatus = "select * from TABLET.WHITELIST WHERE MSISDN = '5490019137'";
        ResultSet rs = executeSelectQuery(con, changeGSMStatus);
        try {
            while (rs.next()) {
                nameNo = rs.getString(2);
                Allure.step("Execute DB Select Query: " + changeGSMStatus + "Document No select value: " + nameNo + " - Successfull", Status.PASSED);
                break;
            }
            rs.close();
            close(con);
            return nameNo;

        } catch (SQLException e) {
            Allure.step("DB Select Error:" + "A fail occured on query." + e.getMessage(), Status.FAILED);

            e.printStackTrace();
            return null;
        }
    }

    public String createTckn(String tckn) {
        String tcknoNo = "";
        con = getConnection("jdbc:oracle:thin:@izmcon5dt-scan:1521/SRV_TBCEBTRL", "TESTUSER", "TESTUSER");

        String tcknSerialNumber = tckn.substring(0,9);

        System.out.println("tc no: " + tckn);
        System.out.println("tc seri no: " + tcknSerialNumber);


        commonLib.setGlobalVariables("tcknoSerialNumber",tcknSerialNumber);


        String addTcknToDB = "    INSERT INTO PROVISIONING_MGNT.MERNIS_CUSTOMER_INFO (\n" +
                "    tc_id,\n" +
                "    birthplace_code,\n" +
                "    name,\n" +
                "    surname,\n" +
                "    sex,\n" +
                "    father_name,\n" +
                "    mother_name,\n" +
                "    date_of_birth_year,\n" +
                "    date_of_birth_month,\n" +
                "    date_of_birth_day,\n" +
                "    place_of_birth,\n" +
                "    status,\n" +
                "    marital_status,\n" +
                "    province_code,\n" +
                "    province_name,\n" +
                "    county_code,\n" +
                "    county_name,\n" +
                "    volume_code,\n" +
                "    volume_name,\n" +
                "    family_sequence_number,\n" +
                "    person_sequence_number,\n" +
                "    document_no) \n" +
                "    VALUES (\n" +
                "    "+ tckn +",\n" +
                "    '1542',\n" +
                "    'SOTO',\n" +
                "    'SOTO',\n" +
                "    'Male',\n" +
                "    'SOTO',\n" +
                "    'SOTO',\n" +
                "    '1992',\n" +
                "    '12',\n" +
                "    '12',\n" +
                "    'ADANA',\n" +
                "    'Open',\n" +
                "    'Single',\n" +
                "    '1',\n" +
                "    'ADANA',\n" +
                "    '1101',\n" +
                "    'AHMET',\n" +
                "    '1',\n" +
                "    'MERKEZ',\n" +
                "    '39',\n" +
                "    '3',\n" +
                "    "+ tcknSerialNumber +")\n" +
                "    ";
        updateSelectQuery(con, addTcknToDB);
        try {
            //while (rs.next()) {
            // tcknoNo = rs.getString(2);
            Allure.step("Execute DB Select Query: " + addTcknToDB + "Document No select value: " + tcknoNo + " - Successfull", Status.PASSED);
            //  break;
            //}
            con.close();
            close(con);
            return tckn;

        } catch (SQLException e) {
            Allure.step("DB Select Error:" + "A fail occured on query." + e.getMessage(), Status.FAILED);

            e.printStackTrace();
            return null;
        }
    }


}

