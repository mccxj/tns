/*      */ package oracle.jdbc.driver;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ class OracleSqlReadOnly
/*      */ {
/*      */   private static final int BASE = 0;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int BASE_1 = 1;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int BASE_2 = 2;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int B_STRING = 3;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int B_NAME = 4;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int B_C_COMMENT = 5;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int B_C_COMMENT_1 = 6;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int B_COMMENT = 7;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int PARAMETER = 8;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int TOKEN = 9;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int B_EGIN = 10;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int BE_GIN = 11;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int BEG_IN = 12;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int BEGI_N = 13;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int BEGIN_ = 14;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int C_ALL = 15;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int CA_LL = 16;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int CAL_L = 17;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int CALL_ = 18;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int D_Eetc = 19;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int DE_etc = 20;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int DEC_LARE = 21;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int DECL_ARE = 22;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int DECLA_RE = 23;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int DECLAR_E = 24;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int DECLARE_ = 25;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int DEL_ETE = 26;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int DELE_TE = 27;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int DELET_E = 28;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int DELETE_ = 29;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int I_NSERT = 30;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int IN_SERT = 31;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int INS_ERT = 32;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int INSE_RT = 33;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int INSER_T = 34;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int INSERT_ = 35;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int S_ELECT = 36;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int SE_LECT = 37;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int SEL_ECT = 38;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int SELE_CT = 39;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int SELEC_T = 40;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int SELECT_ = 41;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int U_PDATE = 42;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int UP_DATE = 43;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int UPD_ATE = 44;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int UPDA_TE = 45;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int UPDAT_E = 46;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int UPDATE_ = 47;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int M_ERGE = 48;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int ME_RGE = 49;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int MER_GE = 50;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int MERG_E = 51;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int MERGE_ = 52;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int W_ITH = 53;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int WI_TH = 54;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int WIT_H = 55;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int WITH_ = 56;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int KNOW_KIND = 57;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int KNOW_KIND_1 = 58;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int KNOW_KIND_2 = 59;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int K_STRING = 60;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int K_NAME = 61;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int K_C_COMMENT = 62;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int K_C_COMMENT_1 = 63;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int K_COMMENT = 64;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int K_PARAMETER = 65;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int TOKEN_KK = 66;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int W_HERE = 67;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int WH_ERE = 68;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int WHE_RE = 69;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int WHER_E = 70;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int WHERE_ = 71;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int O_RDER_BY = 72;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int OR_DER_BY = 73;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int ORD_ER_BY = 74;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int ORDE_R_BY = 75;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int ORDER__BY = 76;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int ORDER_xBY = 77;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int ORDER_B_Y = 78;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int ORDER_BY_ = 79;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int ORDER_xBY_CC_1 = 80;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int ORDER_xBY_CC_2 = 81;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int ORDER_xBY_CC_3 = 82;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int ORDER_xBY_C_1 = 83;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int ORDER_xBY_C_2 = 84;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int F_OR_UPDATE = 85;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int FO_R_UPDATE = 86;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int FOR__UPDATE = 87;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int FOR_xUPDATE = 88;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int FOR_U_PDATE = 89;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int FOR_UP_DATE = 90;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int FOR_UPD_ATE = 91;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int FOR_UPDA_TE = 92;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int FOR_UPDAT_E = 93;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int FOR_UPDATE_ = 94;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int FOR_xUPDATE_CC_1 = 95;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int FOR_xUPDATE_CC_2 = 96;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int FOR_xUPDATE_CC_3 = 97;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int FOR_xUPDATE_C_1 = 98;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int FOR_xUPDATE_C_2 = 99;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int B_N_tick = 100;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int B_NCHAR = 101;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int K_N_tick = 102;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int K_NCHAR = 103;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int K_NCHAR_tick = 104;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int B_Q_tickDelimiterCharDelimiterTick = 105;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int B_QTick_delimiterCharDelimiterTick = 106;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int B_QTickDelimiter_charDelimiterTick = 107;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int B_QTickDelimiterChar_delimiterTick = 108;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int B_QTickDelimiterCharDelimiter_tick = 109;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int K_Q_tickDelimiterCharDelimiterTick = 110;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int K_QTick_delimiterCharDelimiterTick = 111;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int K_QTickDelimiter_charDelimiterTick = 112;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int K_QTickDelimiterChar_delimiterTick = 113;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int K_QTickDelimiterCharDelimiter_tick = 114;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int K_EscEtc = 115;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int K_EscQuestion = 116;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int K_EscC_ALL = 117;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int K_EscCA_LL = 118;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int K_EscCAL_L = 119;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int K_EscCALL_ = 120;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int K_EscT = 121;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int K_EscTS_ = 122;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int K_EscD_ = 123;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int K_EscE_SCAPE = 124;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int K_EscES_CAPE = 125;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int K_EscESC_APE = 126;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int K_EscESCA_PE = 127;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int K_EscESCAP_E = 128;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int K_EscESCAPE_ = 129;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int K_EscF_N = 130;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int K_EscFN_ = 131;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int K_EscO_J = 132;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int K_EscOJ_ = 133;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int SKIP_PARAMETER_WHITESPACE = 134;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int A_LTER_SESSION = 135;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int AL_TER_SESSION = 136;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int ALT_ER_SESSION = 137;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int ALTE_R_SESSION = 138;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int ALTER__SESSION = 139;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int ALTER_xSESSION = 140;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int ALTER_S_ESSION = 141;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int ALTER_SE_SSION = 142;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int ALTER_SES_SION = 143;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int ALTER_SESS_ION = 144;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int ALTER_SESSI_ON = 145;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int ALTER_SESSIO_N = 146;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int ALTER_SESSION_ = 147;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int ALTER_xSESSION_CC_1 = 148;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int ALTER_xSESSION_CC_2 = 149;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int ALTER_xSESSION_CC_3 = 150;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int ALTER_xSESSION_C_1 = 151;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int ALTER_xSESSION_C_2 = 152;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int R_ETURNING = 153;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int RE_TURNING = 154;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int RET_URNING = 155;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int RETU_RNING = 156;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int RETUR_NING = 157;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int RETURN_ING = 158;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int RETURNI_NG = 159;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int RETURNIN_G = 160;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int RETURNING_ = 161;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int I_NTO = 162;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int IN_TO = 163;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int INT_O = 164;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int INTO_ = 165;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int LAST_STATE = 166;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int EOKTSS_LAST_STATE = 166;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2343 */   public static final String[] PARSER_STATE_NAME = { "BASE", "BASE_1", "BASE_2", "B_STRING", "B_NAME", "B_C_COMMENT", "B_C_COMMENT_1", "B_COMMENT", "PARAMETER", "TOKEN", "B_EGIN", "BE_GIN", "BEG_IN", "BEGI_N", "BEGIN_", "C_ALL", "CA_LL", "CAL_L", "CALL_", "D_Eetc", "DE_etc", "DEC_LARE", "DECL_ARE", "DECLA_RE", "DECLAR_E", "DECLARE_", "DEL_ETE", "DELE_TE", "DELET_E", "DELETE_", "I_NSERT", "IN_SERT", "INS_ERT", "INSE_RT", "INSER_T", "INSERT_", "S_ELECT", "SE_LECT", "SEL_ECT", "SELE_CT", "SELEC_T", "SELECT_", "U_PDATE", "UP_DATE", "UPD_ATE", "UPDA_TE", "UPDAT_E", "UPDATE_", "M_ERGE", "ME_RGE", "MER_GE", "MERG_E", "MERGE_", "W_ITH", "WI_TH", "WIT_H", "WITH_", "KNOW_KIND", "KNOW_KIND_1", "KNOW_KIND_2", "K_STRING", "K_NAME", "K_C_COMMENT", "K_C_COMMENT_1", "K_COMMENT", "K_PARAMETER", "TOKEN_KK", "W_HERE", "WH_ERE", "WHE_RE", "WHER_E", "WHERE_", "O_RDER_BY", "OR_DER_BY", "ORD_ER_BY", "ORDE_R_BY", "ORDER__BY", "ORDER_xBY", "ORDER_B_Y", "ORDER_BY_", "ORDER_xBY_CC_1", "ORDER_xBY_CC_2", "ORDER_xBY_CC_3", "ORDER_xBY_C_1 ", "ORDER_xBY_C_2 ", "F_OR_UPDATE", "FO_R_UPDATE", "FOR__UPDATE", "FOR_xUPDATE", "FOR_U_PDATE", "FOR_UP_DATE", "FOR_UPD_ATE", "FOR_UPDA_TE", "FOR_UPDAT_E", "FOR_UPDATE_", "FOR_xUPDATE_CC_1", "FOR_xUPDATE_CC_2", "FOR_xUPDATE_CC_3", "FOR_xUPDATE_C_1 ", "FOR_xUPDATE_C_2 ", "B_N_tick", "B_NCHAR", "K_N_tick", "K_NCHAR", "K_NCHAR_tick", "B_Q_tickDelimiterCharDelimiterTick", "B_QTick_delimiterCharDelimiterTick", "B_QTickDelimiter_charDelimiterTick", "B_QTickDelimiterChar_delimiterTick", "B_QTickDelimiterCharDelimiter_tick", "K_Q_tickDelimiterCharDelimiterTick", "K_QTick_delimiterCharDelimiterTick", "K_QTickDelimiter_charDelimiterTick", "K_QTickDelimiterChar_delimiterTick", "K_QTickDelimiterCharDelimiter_tick", "K_EscEtc", "K_EscQuestion", "K_EscC_ALL", "K_EscCA_LL", "K_EscCAL_L", "K_EscCALL_", "K_EscT", "K_EscTS_", "K_EscD_", "K_EscE_SCAPE", "K_EscES_CAPE", "K_EscESC_APE", "K_EscESCA_PE", "K_EscESCAP_E", "K_EscESCAPE_", "K_EscF_N", "K_EscFN_", "K_EscO_J", "K_EscOJ_", "SKIP_PARAMETER_WHITESPACE", "A_LTER_SESSION", "AL_TER_SESSION", "ALT_ER_SESSION", "ALTE_R_SESSION", "ALTER__SESSION", "ALTER_xSESSION", "ALTER_S_ESSION", "ALTER_SE_SSION", "ALTER_SES_SION", "ALTER_SESS_ION", "ALTER_SESSI_ON", "ALTER_SESSIO_N", "ALTER_SESSION_", "ALTER_xSESSION_CC_1", "ALTER_xSESSION_CC_2", "ALTER_xSESSION_CC_3", "ALTER_xSESSION_C_1 ", "ALTER_xSESSION_C_2 ", "R_ETURNING", "RE_TURNING", "RET_URNING", "RETU_RNING", "RETUR_NING", "RETURN_ING", "RETURNI_NG", "RETURNIN_G", "RETURNING_", "I_NTO", "IN_TO", "INT_O", "INTO_", "LAST_STATE" };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2535 */   static final int[][] TRANSITION = new int['¦'][];
/*      */   
/*      */   static final int NO_ACTION = 0;
/*      */   
/*      */   static final int DELETE_ACTION = 1;
/*      */   static final int INSERT_ACTION = 2;
/*      */   static final int MERGE_ACTION = 3;
/*      */   static final int UPDATE_ACTION = 4;
/*      */   static final int PLSQL_ACTION = 5;
/*      */   static final int CALL_ACTION = 6;
/*      */   static final int SELECT_ACTION = 7;
/*      */   static final int OTHER_ACTION = 8;
/*      */   static final int WHERE_ACTION = 9;
/*      */   static final int ORDER_ACTION = 10;
/*      */   static final int ORDER_BY_ACTION = 11;
/*      */   static final int FOR_ACTION = 12;
/*      */   static final int FOR_UPDATE_ACTION = 13;
/*      */   static final int QUESTION_ACTION = 14;
/*      */   static final int PARAMETER_ACTION = 15;
/*      */   static final int END_PARAMETER_ACTION = 16;
/*      */   static final int START_NCHAR_LITERAL_ACTION = 17;
/*      */   static final int END_NCHAR_LITERAL_ACTION = 18;
/*      */   static final int SAVE_DELIMITER_ACTION = 19;
/*      */   static final int LOOK_FOR_DELIMITER_ACTION = 20;
/*      */   static final int ALTER_SESSION_ACTION = 21;
/*      */   static final int RETURNING_ACTION = 22;
/*      */   static final int INTO_ACTION = 23;
/* 2562 */   public static final String[] CBI_ACTION_NAME = { "NO_ACTION", "DELETE_ACTION", "INSERT_ACTION", "MERGE_ACTION", "UPDATE_ACTION", "PLSQL_ACTION", "CALL_ACTION", "SELECT_ACTION", "OTHER_ACTION", "WHERE_ACTION", "ORDER_ACTION", "ORDER_BY_ACTION", "FOR_ACTION", "FOR_UPDATE_ACTION", "QUESTION_ACTION", "PARAMETER_ACTION", "END_PARAMETER_ACTION", "START_NCHAR_LITERAL_ACTION", "END_NCHAR_LITERAL_ACTION", "SAVE_DELIMITER_ACTION", "LOOK_FOR_DELIMITER_ACTION", "ALTER_SESSION_ACTION", "RETURNING_ACTION", "INTO_ACTION" };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2594 */   static final int[][] ACTION = new int['¦'][];
/*      */   
/*      */ 
/*      */   static final int INITIAL_STATE = 0;
/*      */   
/*      */   static final int RESTART_STATE = 66;
/*      */   
/*      */ 
/*      */   static enum ODBCAction
/*      */   {
/* 2604 */     NONE, 
/* 2605 */     COPY, 
/* 2606 */     QUESTION, 
/* 2607 */     SAVE_DELIMITER, 
/* 2608 */     LOOK_FOR_DELIMITER, 
/* 2609 */     FUNCTION, 
/* 2610 */     CALL, 
/* 2611 */     TIME, 
/* 2612 */     TIMESTAMP, 
/* 2613 */     DATE, 
/* 2614 */     ESCAPE, 
/* 2615 */     SCALAR_FUNCTION, 
/* 2616 */     OUTER_JOIN, 
/* 2617 */     UNKNOWN_ESCAPE, 
/* 2618 */     END_ODBC_ESCAPE, 
/* 2619 */     COMMA, 
/* 2620 */     OPEN_PAREN, 
/* 2621 */     CLOSE_PAREN, 
/* 2622 */     BEGIN;
/*      */     
/*      */     private ODBCAction() {} }
/* 2625 */   static final ODBCAction[][] ODBC_ACTION = new ODBCAction['¦'][];
/*      */   static final int cMax = 127;
/*      */   private static final int cMaxLength = 128;
/*      */   
/*      */   private static final int[] copy(int[] paramArrayOfInt) {
/* 2630 */     int[] arrayOfInt = new int[paramArrayOfInt.length];
/* 2631 */     System.arraycopy(paramArrayOfInt, 0, arrayOfInt, 0, paramArrayOfInt.length);
/* 2632 */     return arrayOfInt;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   private static final ODBCAction[] copy(ODBCAction[] paramArrayOfODBCAction)
/*      */   {
/* 2639 */     ODBCAction[] arrayOfODBCAction = new ODBCAction[paramArrayOfODBCAction.length];
/* 2640 */     System.arraycopy(paramArrayOfODBCAction, 0, arrayOfODBCAction, 0, paramArrayOfODBCAction.length);
/* 2641 */     return arrayOfODBCAction;
/*      */   }
/*      */   
/*      */ 
/*      */   private static final int[] newArray(int paramInt1, int paramInt2)
/*      */   {
/* 2647 */     int[] arrayOfInt = new int[paramInt1];
/*      */     
/* 2649 */     for (int i = 0; i < paramInt1; i++) {
/* 2650 */       arrayOfInt[i] = paramInt2;
/*      */     }
/* 2652 */     return arrayOfInt;
/*      */   }
/*      */   
/*      */ 
/*      */   private static final ODBCAction[] newArray(int paramInt, ODBCAction paramODBCAction)
/*      */   {
/* 2658 */     ODBCAction[] arrayOfODBCAction = new ODBCAction[paramInt];
/*      */     
/* 2660 */     for (int i = 0; i < paramInt; i++) {
/* 2661 */       arrayOfODBCAction[i] = paramODBCAction;
/*      */     }
/* 2663 */     return arrayOfODBCAction;
/*      */   }
/*      */   
/*      */   private static final int[] copyReplacing(int[] paramArrayOfInt, int paramInt1, int paramInt2)
/*      */   {
/* 2668 */     int[] arrayOfInt = new int[paramArrayOfInt.length];
/*      */     
/* 2670 */     for (int i = 0; i < arrayOfInt.length; i++)
/*      */     {
/* 2672 */       int j = paramArrayOfInt[i];
/*      */       
/* 2674 */       if (j == paramInt1) {
/* 2675 */         arrayOfInt[i] = paramInt2;
/*      */       } else {
/* 2677 */         arrayOfInt[i] = j;
/*      */       }
/*      */     }
/* 2680 */     return arrayOfInt;
/*      */   }
/*      */   
/*      */   private static final ODBCAction[] copyReplacing(ODBCAction[] paramArrayOfODBCAction, ODBCAction paramODBCAction1, ODBCAction paramODBCAction2)
/*      */   {
/* 2685 */     ODBCAction[] arrayOfODBCAction = new ODBCAction[paramArrayOfODBCAction.length];
/*      */     
/* 2687 */     for (int i = 0; i < arrayOfODBCAction.length; i++)
/*      */     {
/* 2689 */       ODBCAction localODBCAction = paramArrayOfODBCAction[i];
/*      */       
/* 2691 */       if (localODBCAction == paramODBCAction1) {
/* 2692 */         arrayOfODBCAction[i] = paramODBCAction2;
/*      */       } else {
/* 2694 */         arrayOfODBCAction[i] = localODBCAction;
/*      */       }
/*      */     }
/* 2697 */     return arrayOfODBCAction;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   static
/*      */   {
/*      */     try
/*      */     {
/* 2712 */       int[] arrayOfInt1 = { 57, 57, 57, 57, 57, 57, 57, 57, 57, 57, 57, 57, 57, 57, 57, 57, 57, 57, 57, 57, 57, 57, 57, 57, 57, 57, 57, 57, 57, 57, 57, 57, 57, 57, 57, 9, 9, 57, 57, 57, 57, 57, 57, 57, 57, 57, 57, 57, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 57, 57, 57, 57, 57, 57, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 57, 57, 57, 57, 9, 57, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 57, 57, 57, 57, 57 };
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2749 */       int[] arrayOfInt2 = copy(arrayOfInt1);
/* 2750 */       arrayOfInt2[34] = 4;
/* 2751 */       arrayOfInt2[39] = 3;
/* 2752 */       arrayOfInt2[45] = 2;
/* 2753 */       arrayOfInt2[47] = 1;
/* 2754 */       arrayOfInt2[58] = 8;
/* 2755 */       arrayOfInt2[123] = 115;
/*      */       
/*      */ 
/*      */ 
/* 2759 */       int[] arrayOfInt3 = copyReplacing(arrayOfInt2, 57, 0);
/* 2760 */       arrayOfInt3[65] = 135;
/* 2761 */       arrayOfInt3[97] = 135;
/* 2762 */       arrayOfInt3[66] = 10;
/* 2763 */       arrayOfInt3[98] = 10;
/* 2764 */       arrayOfInt3[67] = 15;
/* 2765 */       arrayOfInt3[99] = 15;
/* 2766 */       arrayOfInt3[68] = 19;
/* 2767 */       arrayOfInt3[100] = 19;
/* 2768 */       arrayOfInt3[73] = 30;
/* 2769 */       arrayOfInt3[105] = 30;
/* 2770 */       arrayOfInt3[109] = 48;
/* 2771 */       arrayOfInt3[77] = 48;
/* 2772 */       arrayOfInt3[78] = 100;
/* 2773 */       arrayOfInt3[110] = 100;
/* 2774 */       arrayOfInt3[81] = 105;
/* 2775 */       arrayOfInt3[113] = 105;
/* 2776 */       arrayOfInt3[83] = 36;
/* 2777 */       arrayOfInt3[115] = 36;
/* 2778 */       arrayOfInt3[85] = 42;
/* 2779 */       arrayOfInt3[117] = 42;
/* 2780 */       arrayOfInt3[87] = 53;
/* 2781 */       arrayOfInt3[119] = 53;
/*      */       
/*      */ 
/* 2784 */       int[] arrayOfInt4 = copyReplacing(arrayOfInt2, 9, 66);
/* 2785 */       arrayOfInt4[34] = 61;
/* 2786 */       arrayOfInt4[39] = 60;
/* 2787 */       arrayOfInt4[45] = 59;
/* 2788 */       arrayOfInt4[47] = 58;
/* 2789 */       arrayOfInt4[58] = 134;
/* 2790 */       arrayOfInt4[32] = 57;
/* 2791 */       arrayOfInt4[32] = 57;
/* 2792 */       arrayOfInt4[9] = 57;
/* 2793 */       arrayOfInt4[10] = 57;
/* 2794 */       arrayOfInt4[13] = 57;
/* 2795 */       arrayOfInt4[61] = 57;
/*      */       
/*      */ 
/* 2798 */       int[] arrayOfInt5 = copyReplacing(arrayOfInt4, 9, 66);
/* 2799 */       arrayOfInt5[78] = 102;
/* 2800 */       arrayOfInt5[110] = 102;
/* 2801 */       arrayOfInt5[81] = 110;
/* 2802 */       arrayOfInt5[113] = 110;
/* 2803 */       arrayOfInt5[87] = 67;
/* 2804 */       arrayOfInt5[119] = 67;
/* 2805 */       arrayOfInt5[79] = 72;
/* 2806 */       arrayOfInt5[111] = 72;
/* 2807 */       arrayOfInt5[70] = 85;
/* 2808 */       arrayOfInt5[102] = 85;
/* 2809 */       arrayOfInt5[82] = 153;
/* 2810 */       arrayOfInt5[114] = 153;
/* 2811 */       arrayOfInt5[73] = 162;
/* 2812 */       arrayOfInt5[105] = 162;
/*      */       
/*      */ 
/*      */ 
/* 2816 */       int[] arrayOfInt6 = copyReplacing(arrayOfInt5, 57, 115);
/*      */       
/* 2818 */       arrayOfInt6[63] = 116;
/* 2819 */       arrayOfInt6[99] = 117;
/* 2820 */       arrayOfInt6[67] = 117;
/* 2821 */       arrayOfInt6[116] = 121;
/* 2822 */       arrayOfInt6[84] = 121;
/* 2823 */       arrayOfInt6[100] = 123;
/* 2824 */       arrayOfInt6[68] = 123;
/* 2825 */       arrayOfInt6[101] = 124;
/* 2826 */       arrayOfInt6[69] = 124;
/* 2827 */       arrayOfInt6[102] = 130;
/* 2828 */       arrayOfInt6[70] = 130;
/* 2829 */       arrayOfInt6[111] = 132;
/* 2830 */       arrayOfInt6[79] = 132;
/*      */       
/*      */ 
/*      */ 
/* 2834 */       TRANSITION[0] = arrayOfInt3;
/* 2835 */       TRANSITION[1] = copy(arrayOfInt3);
/* 2836 */       TRANSITION[1][42] = 5;
/* 2837 */       TRANSITION[2] = copy(arrayOfInt3);
/* 2838 */       TRANSITION[2][45] = 7;
/* 2839 */       TRANSITION[3] = newArray(128, 3);
/* 2840 */       TRANSITION[3][39] = 0;
/* 2841 */       TRANSITION[100] = copy(arrayOfInt2);
/* 2842 */       TRANSITION[100][39] = 101;
/* 2843 */       TRANSITION[101] = newArray(128, 101);
/* 2844 */       TRANSITION[101][39] = 0;
/*      */       
/* 2846 */       TRANSITION[105] = copy(arrayOfInt2);
/* 2847 */       TRANSITION[105][39] = 106;
/* 2848 */       TRANSITION[106] = newArray(128, 107);
/* 2849 */       TRANSITION[107] = newArray(128, 107);
/*      */       
/* 2851 */       TRANSITION[108] = newArray(128, 109);
/* 2852 */       TRANSITION[109] = newArray(128, 107);
/* 2853 */       TRANSITION[109][39] = 0;
/*      */       
/*      */ 
/* 2856 */       TRANSITION[4] = newArray(128, 4);
/* 2857 */       TRANSITION[4][34] = 0;
/* 2858 */       TRANSITION[5] = newArray(128, 5);
/* 2859 */       TRANSITION[5][42] = 6;
/* 2860 */       TRANSITION[6] = newArray(128, 5);
/* 2861 */       TRANSITION[6][42] = 6;
/* 2862 */       TRANSITION[6][47] = 0;
/* 2863 */       TRANSITION[7] = newArray(128, 7);
/* 2864 */       TRANSITION[7][10] = 0;
/* 2865 */       TRANSITION[8] = copyReplacing(arrayOfInt2, 9, 8);
/* 2866 */       TRANSITION[9] = arrayOfInt2;
/* 2867 */       TRANSITION[10] = copy(arrayOfInt2);
/* 2868 */       TRANSITION[10][69] = 11;
/* 2869 */       TRANSITION[10][101] = 11;
/* 2870 */       TRANSITION[11] = copy(arrayOfInt2);
/* 2871 */       TRANSITION[11][71] = 12;
/* 2872 */       TRANSITION[11][103] = 12;
/* 2873 */       TRANSITION[12] = copy(arrayOfInt2);
/* 2874 */       TRANSITION[12][73] = 13;
/* 2875 */       TRANSITION[12][105] = 13;
/* 2876 */       TRANSITION[13] = copy(arrayOfInt2);
/* 2877 */       TRANSITION[13][78] = 14;
/* 2878 */       TRANSITION[13][110] = 14;
/* 2879 */       TRANSITION[14] = arrayOfInt5;
/* 2880 */       TRANSITION[15] = copy(arrayOfInt2);
/* 2881 */       TRANSITION[15][65] = 16;
/* 2882 */       TRANSITION[15][97] = 16;
/* 2883 */       TRANSITION[16] = copy(arrayOfInt2);
/* 2884 */       TRANSITION[16][76] = 17;
/* 2885 */       TRANSITION[16][108] = 17;
/* 2886 */       TRANSITION[17] = copy(arrayOfInt2);
/* 2887 */       TRANSITION[17][76] = 18;
/* 2888 */       TRANSITION[17][108] = 18;
/* 2889 */       TRANSITION[18] = arrayOfInt5;
/* 2890 */       TRANSITION[19] = copy(arrayOfInt2);
/* 2891 */       TRANSITION[19][69] = 20;
/* 2892 */       TRANSITION[19][101] = 20;
/* 2893 */       TRANSITION[20] = copy(arrayOfInt2);
/* 2894 */       TRANSITION[20][67] = 21;
/* 2895 */       TRANSITION[20][99] = 21;
/* 2896 */       TRANSITION[20][76] = 26;
/* 2897 */       TRANSITION[20][108] = 26;
/* 2898 */       TRANSITION[21] = copy(arrayOfInt2);
/* 2899 */       TRANSITION[21][76] = 22;
/* 2900 */       TRANSITION[21][108] = 22;
/* 2901 */       TRANSITION[22] = copy(arrayOfInt2);
/* 2902 */       TRANSITION[22][65] = 23;
/* 2903 */       TRANSITION[22][97] = 23;
/* 2904 */       TRANSITION[23] = copy(arrayOfInt2);
/* 2905 */       TRANSITION[23][82] = 24;
/* 2906 */       TRANSITION[23][114] = 24;
/* 2907 */       TRANSITION[24] = copy(arrayOfInt2);
/* 2908 */       TRANSITION[24][69] = 25;
/* 2909 */       TRANSITION[24][101] = 25;
/* 2910 */       TRANSITION[25] = arrayOfInt5;
/* 2911 */       TRANSITION[26] = copy(arrayOfInt2);
/* 2912 */       TRANSITION[26][69] = 27;
/* 2913 */       TRANSITION[26][101] = 27;
/* 2914 */       TRANSITION[27] = copy(arrayOfInt2);
/* 2915 */       TRANSITION[27][84] = 28;
/* 2916 */       TRANSITION[27][116] = 28;
/* 2917 */       TRANSITION[28] = copy(arrayOfInt2);
/* 2918 */       TRANSITION[28][69] = 29;
/* 2919 */       TRANSITION[28][101] = 29;
/* 2920 */       TRANSITION[29] = arrayOfInt5;
/* 2921 */       TRANSITION[30] = copy(arrayOfInt2);
/* 2922 */       TRANSITION[30][78] = 31;
/* 2923 */       TRANSITION[30][110] = 31;
/* 2924 */       TRANSITION[31] = copy(arrayOfInt2);
/* 2925 */       TRANSITION[31][83] = 32;
/* 2926 */       TRANSITION[31][115] = 32;
/* 2927 */       TRANSITION[32] = copy(arrayOfInt2);
/* 2928 */       TRANSITION[32][69] = 33;
/* 2929 */       TRANSITION[32][101] = 33;
/* 2930 */       TRANSITION[33] = copy(arrayOfInt2);
/* 2931 */       TRANSITION[33][82] = 34;
/* 2932 */       TRANSITION[33][114] = 34;
/* 2933 */       TRANSITION[34] = copy(arrayOfInt2);
/* 2934 */       TRANSITION[34][84] = 35;
/* 2935 */       TRANSITION[34][116] = 35;
/* 2936 */       TRANSITION[35] = arrayOfInt5;
/* 2937 */       TRANSITION[36] = copy(arrayOfInt2);
/* 2938 */       TRANSITION[36][69] = 37;
/* 2939 */       TRANSITION[36][101] = 37;
/* 2940 */       TRANSITION[37] = copy(arrayOfInt2);
/* 2941 */       TRANSITION[37][76] = 38;
/* 2942 */       TRANSITION[37][108] = 38;
/* 2943 */       TRANSITION[38] = copy(arrayOfInt2);
/* 2944 */       TRANSITION[38][69] = 39;
/* 2945 */       TRANSITION[38][101] = 39;
/* 2946 */       TRANSITION[39] = copy(arrayOfInt2);
/* 2947 */       TRANSITION[39][67] = 40;
/* 2948 */       TRANSITION[39][99] = 40;
/* 2949 */       TRANSITION[40] = copy(arrayOfInt2);
/* 2950 */       TRANSITION[40][84] = 41;
/* 2951 */       TRANSITION[40][116] = 41;
/* 2952 */       TRANSITION[41] = arrayOfInt5;
/* 2953 */       TRANSITION[42] = copy(arrayOfInt2);
/* 2954 */       TRANSITION[42][80] = 43;
/* 2955 */       TRANSITION[42][112] = 43;
/* 2956 */       TRANSITION[43] = copy(arrayOfInt2);
/* 2957 */       TRANSITION[43][68] = 44;
/* 2958 */       TRANSITION[43][100] = 44;
/* 2959 */       TRANSITION[44] = copy(arrayOfInt2);
/* 2960 */       TRANSITION[44][65] = 45;
/* 2961 */       TRANSITION[44][97] = 45;
/* 2962 */       TRANSITION[45] = copy(arrayOfInt2);
/* 2963 */       TRANSITION[45][84] = 46;
/* 2964 */       TRANSITION[45][116] = 46;
/* 2965 */       TRANSITION[46] = copy(arrayOfInt2);
/* 2966 */       TRANSITION[46][69] = 47;
/* 2967 */       TRANSITION[46][101] = 47;
/* 2968 */       TRANSITION[47] = arrayOfInt5;
/* 2969 */       TRANSITION[48] = copy(arrayOfInt2);
/* 2970 */       TRANSITION[48][69] = 49;
/* 2971 */       TRANSITION[48][101] = 49;
/* 2972 */       TRANSITION[49] = copy(arrayOfInt2);
/* 2973 */       TRANSITION[49][82] = 50;
/* 2974 */       TRANSITION[49][114] = 50;
/* 2975 */       TRANSITION[50] = copy(arrayOfInt2);
/* 2976 */       TRANSITION[50][71] = 51;
/* 2977 */       TRANSITION[50][103] = 51;
/* 2978 */       TRANSITION[51] = copy(arrayOfInt2);
/* 2979 */       TRANSITION[51][69] = 52;
/* 2980 */       TRANSITION[51][101] = 52;
/* 2981 */       TRANSITION[52] = arrayOfInt5;
/* 2982 */       TRANSITION[53] = copy(arrayOfInt2);
/* 2983 */       TRANSITION[53][73] = 54;
/* 2984 */       TRANSITION[53][105] = 54;
/* 2985 */       TRANSITION[54] = copy(arrayOfInt2);
/* 2986 */       TRANSITION[54][84] = 55;
/* 2987 */       TRANSITION[54][116] = 55;
/* 2988 */       TRANSITION[55] = copy(arrayOfInt2);
/* 2989 */       TRANSITION[55][72] = 56;
/* 2990 */       TRANSITION[55][104] = 56;
/* 2991 */       TRANSITION[56] = arrayOfInt5;
/* 2992 */       TRANSITION[66] = arrayOfInt4;
/* 2993 */       TRANSITION[58] = copy(arrayOfInt5);
/* 2994 */       TRANSITION[58][42] = 62;
/* 2995 */       TRANSITION[59] = copy(arrayOfInt5);
/* 2996 */       TRANSITION[59][45] = 64;
/* 2997 */       TRANSITION[62] = newArray(128, 62);
/* 2998 */       TRANSITION[62][42] = 63;
/* 2999 */       TRANSITION[63] = newArray(128, 62);
/* 3000 */       TRANSITION[63][42] = 63;
/* 3001 */       TRANSITION[63][47] = 57;
/* 3002 */       TRANSITION[64] = newArray(128, 64);
/* 3003 */       TRANSITION[64][10] = 57;
/* 3004 */       TRANSITION[61] = newArray(128, 61);
/* 3005 */       TRANSITION[61][34] = 57;
/* 3006 */       TRANSITION[60] = newArray(128, 60);
/* 3007 */       TRANSITION[60][39] = 57;
/* 3008 */       TRANSITION[65] = copyReplacing(arrayOfInt4, 66, 65);
/* 3009 */       TRANSITION[''] = copyReplacing(arrayOfInt4, 66, 65);
/* 3010 */       TRANSITION[''][32] = '';
/* 3011 */       TRANSITION[''][10] = '';
/* 3012 */       TRANSITION[''][13] = '';
/* 3013 */       TRANSITION[''][9] = '';
/*      */       
/*      */ 
/* 3016 */       TRANSITION[57] = arrayOfInt5;
/* 3017 */       TRANSITION[67] = copy(arrayOfInt4);
/* 3018 */       TRANSITION[67][72] = 68;
/* 3019 */       TRANSITION[67][104] = 68;
/* 3020 */       TRANSITION[68] = copy(arrayOfInt4);
/* 3021 */       TRANSITION[68][69] = 69;
/* 3022 */       TRANSITION[68][101] = 69;
/* 3023 */       TRANSITION[69] = copy(arrayOfInt4);
/* 3024 */       TRANSITION[69][82] = 70;
/* 3025 */       TRANSITION[69][114] = 70;
/* 3026 */       TRANSITION[70] = copy(arrayOfInt4);
/* 3027 */       TRANSITION[70][69] = 71;
/* 3028 */       TRANSITION[70][101] = 71;
/* 3029 */       TRANSITION[71] = arrayOfInt5;
/*      */       
/* 3031 */       TRANSITION[72] = copy(arrayOfInt4);
/* 3032 */       TRANSITION[72][82] = 73;
/* 3033 */       TRANSITION[72][114] = 73;
/* 3034 */       TRANSITION[73] = copy(arrayOfInt4);
/* 3035 */       TRANSITION[73][68] = 74;
/* 3036 */       TRANSITION[73][100] = 74;
/* 3037 */       TRANSITION[74] = copy(arrayOfInt4);
/* 3038 */       TRANSITION[74][69] = 75;
/* 3039 */       TRANSITION[74][101] = 75;
/* 3040 */       TRANSITION[75] = copy(arrayOfInt4);
/* 3041 */       TRANSITION[75][82] = 76;
/* 3042 */       TRANSITION[75][114] = 76;
/* 3043 */       TRANSITION[76] = copyReplacing(arrayOfInt5, 57, 77);
/* 3044 */       TRANSITION[76][47] = 80;
/* 3045 */       TRANSITION[76][45] = 83;
/*      */       
/* 3047 */       TRANSITION[77] = copyReplacing(arrayOfInt5, 57, 77);
/* 3048 */       TRANSITION[77][47] = 80;
/* 3049 */       TRANSITION[80] = copy(arrayOfInt5);
/* 3050 */       TRANSITION[80][42] = 81;
/* 3051 */       TRANSITION[81] = newArray(128, 81);
/* 3052 */       TRANSITION[81][42] = 82;
/* 3053 */       TRANSITION[82] = newArray(128, 81);
/* 3054 */       TRANSITION[82][47] = 77;
/*      */       
/* 3056 */       TRANSITION[77][45] = 83;
/* 3057 */       TRANSITION[83] = copy(arrayOfInt5);
/* 3058 */       TRANSITION[83][45] = 84;
/* 3059 */       TRANSITION[84] = newArray(128, 84);
/* 3060 */       TRANSITION[84][10] = 77;
/*      */       
/*      */ 
/* 3063 */       TRANSITION[77][66] = 78;
/* 3064 */       TRANSITION[77][98] = 78;
/* 3065 */       TRANSITION[78] = copy(arrayOfInt4);
/* 3066 */       TRANSITION[78][89] = 79;
/* 3067 */       TRANSITION[78][121] = 79;
/* 3068 */       TRANSITION[79] = arrayOfInt5;
/*      */       
/* 3070 */       TRANSITION[85] = copy(arrayOfInt5);
/* 3071 */       TRANSITION[85][79] = 86;
/* 3072 */       TRANSITION[85][111] = 86;
/* 3073 */       TRANSITION[86] = copy(arrayOfInt5);
/* 3074 */       TRANSITION[86][82] = 87;
/* 3075 */       TRANSITION[86][114] = 87;
/* 3076 */       TRANSITION[87] = copyReplacing(arrayOfInt4, 57, 88);
/* 3077 */       TRANSITION[87][47] = 95;
/* 3078 */       TRANSITION[87][45] = 98;
/*      */       
/* 3080 */       TRANSITION[88] = copyReplacing(arrayOfInt4, 57, 88);
/* 3081 */       TRANSITION[88][47] = 95;
/* 3082 */       TRANSITION[95] = copy(arrayOfInt5);
/* 3083 */       TRANSITION[95][42] = 96;
/* 3084 */       TRANSITION[96] = newArray(128, 96);
/* 3085 */       TRANSITION[96][42] = 97;
/* 3086 */       TRANSITION[97] = newArray(128, 96);
/* 3087 */       TRANSITION[97][47] = 88;
/*      */       
/* 3089 */       TRANSITION[88][45] = 98;
/* 3090 */       TRANSITION[98] = copy(arrayOfInt5);
/* 3091 */       TRANSITION[98][45] = 99;
/* 3092 */       TRANSITION[99] = newArray(128, 99);
/* 3093 */       TRANSITION[99][10] = 88;
/*      */       
/*      */ 
/* 3096 */       TRANSITION[88][85] = 89;
/* 3097 */       TRANSITION[88][117] = 89;
/* 3098 */       TRANSITION[89] = copy(arrayOfInt5);
/* 3099 */       TRANSITION[89][80] = 90;
/* 3100 */       TRANSITION[89][112] = 90;
/* 3101 */       TRANSITION[90] = copy(arrayOfInt5);
/* 3102 */       TRANSITION[90][68] = 91;
/* 3103 */       TRANSITION[90][100] = 91;
/* 3104 */       TRANSITION[91] = copy(arrayOfInt5);
/* 3105 */       TRANSITION[91][65] = 92;
/* 3106 */       TRANSITION[91][97] = 92;
/* 3107 */       TRANSITION[92] = copy(arrayOfInt5);
/* 3108 */       TRANSITION[92][84] = 93;
/* 3109 */       TRANSITION[92][116] = 93;
/* 3110 */       TRANSITION[93] = copy(arrayOfInt5);
/* 3111 */       TRANSITION[93][69] = 94;
/* 3112 */       TRANSITION[93][101] = 94;
/* 3113 */       TRANSITION[94] = arrayOfInt5;
/*      */       
/* 3115 */       TRANSITION[''] = copy(arrayOfInt5);
/* 3116 */       TRANSITION[''][69] = '';
/* 3117 */       TRANSITION[''][101] = '';
/* 3118 */       TRANSITION[''] = copy(arrayOfInt5);
/* 3119 */       TRANSITION[''][84] = '';
/* 3120 */       TRANSITION[''][116] = '';
/* 3121 */       TRANSITION[''] = copy(arrayOfInt5);
/* 3122 */       TRANSITION[''][85] = '';
/* 3123 */       TRANSITION[''][117] = '';
/* 3124 */       TRANSITION[''] = copy(arrayOfInt5);
/* 3125 */       TRANSITION[''][82] = '';
/* 3126 */       TRANSITION[''][114] = '';
/* 3127 */       TRANSITION[''] = copy(arrayOfInt5);
/* 3128 */       TRANSITION[''][78] = '';
/* 3129 */       TRANSITION[''][110] = '';
/* 3130 */       TRANSITION[''] = copy(arrayOfInt5);
/* 3131 */       TRANSITION[''][73] = '';
/* 3132 */       TRANSITION[''][105] = '';
/* 3133 */       TRANSITION[''] = copy(arrayOfInt5);
/* 3134 */       TRANSITION[''][78] = ' ';
/* 3135 */       TRANSITION[''][110] = ' ';
/* 3136 */       TRANSITION[' '] = copy(arrayOfInt5);
/* 3137 */       TRANSITION[' '][71] = '¡';
/* 3138 */       TRANSITION[' '][103] = '¡';
/* 3139 */       TRANSITION['¡'] = arrayOfInt5;
/*      */       
/* 3141 */       TRANSITION['¢'] = copy(arrayOfInt5);
/* 3142 */       TRANSITION['¢'][78] = '£';
/* 3143 */       TRANSITION['¢'][110] = '£';
/* 3144 */       TRANSITION['£'] = copy(arrayOfInt5);
/* 3145 */       TRANSITION['£'][84] = '¤';
/* 3146 */       TRANSITION['£'][116] = '¤';
/* 3147 */       TRANSITION['¤'] = copy(arrayOfInt5);
/* 3148 */       TRANSITION['¤'][79] = '¥';
/* 3149 */       TRANSITION['¤'][111] = '¥';
/* 3150 */       TRANSITION['¥'] = copy(arrayOfInt5);
/*      */       
/*      */ 
/* 3153 */       TRANSITION[102] = copy(arrayOfInt4);
/* 3154 */       TRANSITION[102][39] = 103;
/* 3155 */       TRANSITION[103] = newArray(128, 103);
/* 3156 */       TRANSITION[103][39] = 104;
/*      */       
/* 3158 */       TRANSITION[104] = newArray(128, 57);
/* 3159 */       TRANSITION[104][39] = 103;
/*      */       
/* 3161 */       TRANSITION[110] = copy(arrayOfInt5);
/* 3162 */       TRANSITION[110][39] = 111;
/* 3163 */       TRANSITION[111] = newArray(128, 112);
/* 3164 */       TRANSITION[112] = newArray(128, 112);
/*      */       
/* 3166 */       TRANSITION[113] = newArray(128, 114);
/* 3167 */       TRANSITION[114] = newArray(128, 112);
/* 3168 */       TRANSITION[114][39] = 57;
/*      */       
/*      */ 
/* 3171 */       TRANSITION[115] = arrayOfInt6;
/* 3172 */       TRANSITION[116] = arrayOfInt5;
/* 3173 */       TRANSITION[117] = copy(arrayOfInt5);
/* 3174 */       TRANSITION[117][97] = 118;
/* 3175 */       TRANSITION[117][65] = 118;
/* 3176 */       TRANSITION[118] = copy(arrayOfInt5);
/* 3177 */       TRANSITION[118][108] = 119;
/* 3178 */       TRANSITION[118][76] = 119;
/* 3179 */       TRANSITION[119] = copy(arrayOfInt5);
/* 3180 */       TRANSITION[119][108] = 120;
/* 3181 */       TRANSITION[119][76] = 120;
/* 3182 */       TRANSITION[120] = arrayOfInt5;
/* 3183 */       TRANSITION[121] = copy(arrayOfInt5);
/* 3184 */       TRANSITION[121][115] = 122;
/* 3185 */       TRANSITION[121][83] = 122;
/* 3186 */       TRANSITION[122] = arrayOfInt5;
/* 3187 */       TRANSITION[123] = arrayOfInt5;
/* 3188 */       TRANSITION[124] = copy(arrayOfInt5);
/* 3189 */       TRANSITION[124][115] = 125;
/* 3190 */       TRANSITION[124][83] = 125;
/* 3191 */       TRANSITION[125] = copy(arrayOfInt5);
/* 3192 */       TRANSITION[125][99] = 126;
/* 3193 */       TRANSITION[125][67] = 126;
/* 3194 */       TRANSITION[126] = copy(arrayOfInt5);
/* 3195 */       TRANSITION[126][97] = 127;
/* 3196 */       TRANSITION[126][65] = 127;
/* 3197 */       TRANSITION[127] = copy(arrayOfInt5);
/* 3198 */       TRANSITION[127][112] = '';
/* 3199 */       TRANSITION[127][80] = '';
/* 3200 */       TRANSITION[''] = copy(arrayOfInt5);
/* 3201 */       TRANSITION[''][101] = '';
/* 3202 */       TRANSITION[''][69] = '';
/* 3203 */       TRANSITION[''] = arrayOfInt5;
/* 3204 */       TRANSITION[''] = copy(arrayOfInt5);
/* 3205 */       TRANSITION[''][110] = '';
/* 3206 */       TRANSITION[''][78] = '';
/* 3207 */       TRANSITION[''] = arrayOfInt5;
/* 3208 */       TRANSITION[''] = copy(arrayOfInt5);
/* 3209 */       TRANSITION[''][106] = '';
/* 3210 */       TRANSITION[''][74] = '';
/* 3211 */       TRANSITION[''] = arrayOfInt5;
/* 3212 */       TRANSITION[''] = copy(arrayOfInt2);
/* 3213 */       TRANSITION[''][76] = '';
/* 3214 */       TRANSITION[''][108] = '';
/* 3215 */       TRANSITION[''] = copy(arrayOfInt2);
/* 3216 */       TRANSITION[''][84] = '';
/* 3217 */       TRANSITION[''][116] = '';
/* 3218 */       TRANSITION[''] = copy(arrayOfInt2);
/* 3219 */       TRANSITION[''][69] = '';
/* 3220 */       TRANSITION[''][101] = '';
/* 3221 */       TRANSITION[''] = copy(arrayOfInt2);
/* 3222 */       TRANSITION[''][82] = '';
/* 3223 */       TRANSITION[''][114] = '';
/* 3224 */       TRANSITION[''] = copyReplacing(arrayOfInt4, 57, 140);
/* 3225 */       TRANSITION[''][47] = '';
/* 3226 */       TRANSITION[''][45] = '';
/*      */       
/* 3228 */       TRANSITION[''] = copyReplacing(arrayOfInt4, 57, 140);
/* 3229 */       TRANSITION[''][47] = '';
/* 3230 */       TRANSITION[''] = copy(arrayOfInt5);
/* 3231 */       TRANSITION[''][42] = '';
/* 3232 */       TRANSITION[''] = newArray(128, 149);
/* 3233 */       TRANSITION[''][42] = '';
/* 3234 */       TRANSITION[''] = newArray(128, 149);
/* 3235 */       TRANSITION[''][47] = '';
/*      */       
/* 3237 */       TRANSITION[''][45] = '';
/* 3238 */       TRANSITION[''] = copy(arrayOfInt5);
/* 3239 */       TRANSITION[''][45] = '';
/* 3240 */       TRANSITION[''] = newArray(128, 152);
/* 3241 */       TRANSITION[''][10] = '';
/* 3242 */       TRANSITION[''][83] = '';
/* 3243 */       TRANSITION[''][115] = '';
/*      */       
/* 3245 */       TRANSITION[''] = copy(arrayOfInt2);
/* 3246 */       TRANSITION[''][69] = '';
/* 3247 */       TRANSITION[''][101] = '';
/* 3248 */       TRANSITION[''] = copy(arrayOfInt2);
/* 3249 */       TRANSITION[''][83] = '';
/* 3250 */       TRANSITION[''][115] = '';
/* 3251 */       TRANSITION[''] = copy(arrayOfInt2);
/* 3252 */       TRANSITION[''][83] = '';
/* 3253 */       TRANSITION[''][115] = '';
/* 3254 */       TRANSITION[''] = copy(arrayOfInt2);
/* 3255 */       TRANSITION[''][73] = '';
/* 3256 */       TRANSITION[''][105] = '';
/* 3257 */       TRANSITION[''] = copy(arrayOfInt2);
/* 3258 */       TRANSITION[''][79] = '';
/* 3259 */       TRANSITION[''][111] = '';
/* 3260 */       TRANSITION[''] = copy(arrayOfInt2);
/* 3261 */       TRANSITION[''][78] = '';
/* 3262 */       TRANSITION[''][110] = '';
/* 3263 */       TRANSITION[''] = arrayOfInt5;
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3270 */       Object localObject1 = newArray(128, 0);
/*      */       
/* 3272 */       Object localObject2 = copy((int[])localObject1);
/* 3273 */       localObject2[63] = 14;
/*      */       
/* 3275 */       Object localObject3 = copy((int[])localObject2);
/* 3276 */       localObject3[123] = 5;
/*      */       
/* 3278 */       Object localObject4 = new int[''];
/*      */       
/* 3280 */       for (int i = 0; i < localObject4.length; i++) {
/* 3281 */         if (TRANSITION[8][i] == 8) {
/* 3282 */           localObject4[i] = 15;
/*      */         } else
/* 3284 */           localObject4[i] = 16;
/*      */       }
/* 3286 */       Object localObject5 = new int[''];
/*      */       
/* 3288 */       for (int j = 0; j < localObject5.length; j++) {
/* 3289 */         if (TRANSITION[65][j] == 65) {
/* 3290 */           localObject5[j] = 15;
/*      */         } else
/* 3292 */           localObject5[j] = 16;
/*      */       }
/* 3294 */       Object localObject6 = copy((int[])localObject5);
/* 3295 */       localObject6[32] = 0;
/* 3296 */       localObject6[10] = 0;
/* 3297 */       localObject6[9] = 0;
/* 3298 */       localObject6[13] = 0;
/*      */       
/* 3300 */       Object localObject7 = copy((int[])localObject1);
/*      */       
/* 3302 */       for (int k = 0; k < localObject7.length; k++) {
/* 3303 */         if (arrayOfInt4[k] != 66)
/* 3304 */           localObject7[k] = 5;
/*      */       }
/* 3306 */       Object localObject8 = copyReplacing((int[])localObject7, 5, 6);
/* 3307 */       int[] arrayOfInt7 = copyReplacing((int[])localObject7, 5, 1);
/* 3308 */       int[] arrayOfInt8 = copyReplacing((int[])localObject7, 5, 2);
/* 3309 */       int[] arrayOfInt9 = copyReplacing((int[])localObject7, 5, 3);
/* 3310 */       int[] arrayOfInt10 = copyReplacing((int[])localObject7, 5, 4);
/* 3311 */       int[] arrayOfInt11 = copyReplacing((int[])localObject7, 5, 7);
/* 3312 */       int[] arrayOfInt12 = copyReplacing((int[])localObject7, 5, 8);
/* 3313 */       arrayOfInt12[123] = 6;
/*      */       
/* 3315 */       int[] arrayOfInt13 = copyReplacing((int[])localObject7, 5, 10);
/*      */       
/* 3317 */       for (int i1 = 0; i1 < arrayOfInt13.length; i1++) {
/* 3318 */         if (arrayOfInt13[i1] == 8) {
/* 3319 */           arrayOfInt13[i1] = 0;
/*      */         }
/*      */       }
/* 3322 */       int[] arrayOfInt14 = copyReplacing(arrayOfInt13, 10, 11);
/* 3323 */       int[] arrayOfInt15 = copyReplacing(arrayOfInt13, 10, 9);
/* 3324 */       int[] arrayOfInt16 = copyReplacing(arrayOfInt13, 10, 12);
/* 3325 */       int[] arrayOfInt17 = copyReplacing(arrayOfInt13, 10, 13);
/* 3326 */       int[] arrayOfInt18 = copyReplacing(arrayOfInt13, 10, 21);
/* 3327 */       int[] arrayOfInt19 = copyReplacing(arrayOfInt13, 10, 22);
/* 3328 */       int[] arrayOfInt20 = copyReplacing(arrayOfInt13, 10, 23);
/*      */       
/* 3330 */       int[] arrayOfInt21 = copy((int[])localObject1);
/* 3331 */       arrayOfInt21[39] = 17;
/*      */       
/* 3333 */       int[] arrayOfInt22 = copyReplacing((int[])localObject1, 0, 18);
/* 3334 */       arrayOfInt22[39] = 0;
/*      */       
/* 3336 */       int[] arrayOfInt23 = copyReplacing((int[])localObject1, 0, 19);
/*      */       
/* 3338 */       int[] arrayOfInt24 = copyReplacing((int[])localObject1, 0, 20);
/*      */       
/* 3340 */       int[] arrayOfInt25 = copy(arrayOfInt24);
/* 3341 */       arrayOfInt25[39] = 0;
/*      */       
/* 3343 */       ACTION[0] = localObject3;
/* 3344 */       ACTION[1] = localObject3;
/* 3345 */       ACTION[2] = localObject3;
/* 3346 */       ACTION[3] = localObject1;
/* 3347 */       ACTION[4] = localObject1;
/* 3348 */       ACTION[5] = localObject1;
/* 3349 */       ACTION[6] = localObject1;
/* 3350 */       ACTION[7] = localObject1;
/* 3351 */       ACTION[8] = localObject4;
/* 3352 */       ACTION[''] = localObject6;
/* 3353 */       ACTION[100] = arrayOfInt21;
/* 3354 */       ACTION[101] = arrayOfInt22;
/* 3355 */       ACTION[105] = localObject1;
/* 3356 */       ACTION[106] = arrayOfInt23;
/* 3357 */       ACTION[107] = arrayOfInt24;
/* 3358 */       ACTION[108] = null;
/* 3359 */       ACTION[109] = arrayOfInt25;
/* 3360 */       ACTION[9] = arrayOfInt12;
/* 3361 */       ACTION[10] = arrayOfInt12;
/* 3362 */       ACTION[11] = arrayOfInt12;
/* 3363 */       ACTION[12] = arrayOfInt12;
/* 3364 */       ACTION[13] = arrayOfInt12;
/* 3365 */       ACTION[14] = localObject7;
/* 3366 */       ACTION[15] = arrayOfInt12;
/* 3367 */       ACTION[16] = arrayOfInt12;
/* 3368 */       ACTION[17] = arrayOfInt12;
/* 3369 */       ACTION[18] = localObject8;
/* 3370 */       ACTION[19] = arrayOfInt12;
/* 3371 */       ACTION[20] = arrayOfInt12;
/* 3372 */       ACTION[21] = arrayOfInt12;
/* 3373 */       ACTION[22] = arrayOfInt12;
/* 3374 */       ACTION[23] = arrayOfInt12;
/* 3375 */       ACTION[24] = arrayOfInt12;
/* 3376 */       ACTION[25] = localObject7;
/* 3377 */       ACTION[26] = arrayOfInt12;
/* 3378 */       ACTION[27] = arrayOfInt12;
/* 3379 */       ACTION[28] = arrayOfInt12;
/* 3380 */       ACTION[29] = arrayOfInt7;
/* 3381 */       ACTION[30] = arrayOfInt12;
/* 3382 */       ACTION[31] = arrayOfInt12;
/* 3383 */       ACTION[32] = arrayOfInt12;
/* 3384 */       ACTION[33] = arrayOfInt12;
/* 3385 */       ACTION[34] = arrayOfInt12;
/* 3386 */       ACTION[35] = arrayOfInt8;
/* 3387 */       ACTION[36] = arrayOfInt12;
/* 3388 */       ACTION[37] = arrayOfInt12;
/* 3389 */       ACTION[38] = arrayOfInt12;
/* 3390 */       ACTION[39] = arrayOfInt12;
/* 3391 */       ACTION[40] = arrayOfInt12;
/* 3392 */       ACTION[41] = arrayOfInt11;
/* 3393 */       ACTION[42] = arrayOfInt12;
/* 3394 */       ACTION[43] = arrayOfInt12;
/* 3395 */       ACTION[44] = arrayOfInt12;
/* 3396 */       ACTION[45] = arrayOfInt12;
/* 3397 */       ACTION[46] = arrayOfInt12;
/* 3398 */       ACTION[47] = arrayOfInt10;
/* 3399 */       ACTION[48] = arrayOfInt12;
/* 3400 */       ACTION[49] = arrayOfInt12;
/* 3401 */       ACTION[50] = arrayOfInt12;
/* 3402 */       ACTION[51] = arrayOfInt12;
/* 3403 */       ACTION[52] = arrayOfInt9;
/* 3404 */       ACTION[53] = arrayOfInt12;
/* 3405 */       ACTION[54] = arrayOfInt12;
/* 3406 */       ACTION[55] = arrayOfInt12;
/* 3407 */       ACTION[56] = arrayOfInt11;
/* 3408 */       ACTION[66] = localObject2;
/* 3409 */       ACTION[58] = localObject2;
/* 3410 */       ACTION[59] = localObject2;
/* 3411 */       ACTION[60] = localObject1;
/* 3412 */       ACTION[61] = localObject1;
/* 3413 */       ACTION[62] = localObject1;
/* 3414 */       ACTION[63] = localObject1;
/* 3415 */       ACTION[64] = localObject1;
/* 3416 */       ACTION[65] = localObject5;
/* 3417 */       ACTION[102] = arrayOfInt21;
/* 3418 */       ACTION[103] = localObject1;
/* 3419 */       ACTION[104] = arrayOfInt22;
/* 3420 */       ACTION[110] = localObject1;
/* 3421 */       ACTION[111] = arrayOfInt23;
/* 3422 */       ACTION[112] = arrayOfInt24;
/* 3423 */       ACTION[113] = null;
/* 3424 */       ACTION[114] = arrayOfInt25;
/*      */       
/* 3426 */       ACTION[57] = localObject2;
/*      */       
/* 3428 */       ACTION[67] = localObject1;
/* 3429 */       ACTION[68] = localObject1;
/* 3430 */       ACTION[69] = localObject1;
/* 3431 */       ACTION[70] = localObject1;
/* 3432 */       ACTION[71] = arrayOfInt15;
/*      */       
/* 3434 */       ACTION[72] = localObject1;
/* 3435 */       ACTION[73] = localObject1;
/* 3436 */       ACTION[74] = localObject1;
/* 3437 */       ACTION[75] = localObject1;
/* 3438 */       ACTION[76] = arrayOfInt13;
/*      */       
/* 3440 */       ACTION[77] = localObject1;
/* 3441 */       ACTION[78] = localObject1;
/* 3442 */       ACTION[79] = arrayOfInt14;
/*      */       
/* 3444 */       ACTION[80] = localObject1;
/* 3445 */       ACTION[81] = localObject1;
/* 3446 */       ACTION[82] = localObject1;
/* 3447 */       ACTION[83] = localObject1;
/* 3448 */       ACTION[84] = localObject1;
/*      */       
/* 3450 */       ACTION[85] = localObject1;
/* 3451 */       ACTION[86] = localObject1;
/* 3452 */       ACTION[87] = arrayOfInt16;
/*      */       
/* 3454 */       ACTION[88] = localObject2;
/* 3455 */       ACTION[89] = localObject1;
/* 3456 */       ACTION[90] = localObject1;
/* 3457 */       ACTION[91] = localObject1;
/* 3458 */       ACTION[92] = localObject1;
/* 3459 */       ACTION[93] = localObject1;
/* 3460 */       ACTION[94] = arrayOfInt17;
/*      */       
/* 3462 */       ACTION[95] = localObject1;
/* 3463 */       ACTION[96] = localObject1;
/* 3464 */       ACTION[97] = localObject1;
/* 3465 */       ACTION[98] = localObject1;
/* 3466 */       ACTION[99] = localObject1;
/*      */       
/* 3468 */       ACTION[115] = copy((int[])localObject1);
/* 3469 */       ACTION[115][63] = 14;
/* 3470 */       ACTION[116] = localObject1;
/* 3471 */       ACTION[117] = localObject1;
/* 3472 */       ACTION[118] = localObject1;
/* 3473 */       ACTION[119] = localObject1;
/* 3474 */       ACTION[120] = localObject1;
/* 3475 */       ACTION[121] = localObject1;
/* 3476 */       ACTION[122] = localObject1;
/* 3477 */       ACTION[123] = localObject1;
/* 3478 */       ACTION[124] = localObject1;
/* 3479 */       ACTION[125] = localObject1;
/* 3480 */       ACTION[126] = localObject1;
/* 3481 */       ACTION[127] = localObject1;
/* 3482 */       ACTION[''] = localObject1;
/* 3483 */       ACTION[''] = localObject1;
/* 3484 */       ACTION[''] = localObject1;
/* 3485 */       ACTION[''] = localObject1;
/* 3486 */       ACTION[''] = localObject1;
/* 3487 */       ACTION[''] = localObject1;
/*      */       
/* 3489 */       ACTION[''] = localObject1;
/* 3490 */       ACTION[''] = localObject1;
/* 3491 */       ACTION[''] = localObject1;
/* 3492 */       ACTION[''] = localObject1;
/* 3493 */       ACTION[''] = arrayOfInt12;
/*      */       
/* 3495 */       ACTION[''] = localObject2;
/* 3496 */       ACTION[''] = localObject1;
/* 3497 */       ACTION[''] = localObject1;
/* 3498 */       ACTION[''] = localObject1;
/* 3499 */       ACTION[''] = localObject1;
/* 3500 */       ACTION[''] = localObject1;
/* 3501 */       ACTION[''] = localObject1;
/* 3502 */       ACTION[''] = arrayOfInt18;
/*      */       
/* 3504 */       ACTION[''] = localObject1;
/* 3505 */       ACTION[''] = localObject1;
/* 3506 */       ACTION[''] = localObject1;
/* 3507 */       ACTION[''] = localObject1;
/* 3508 */       ACTION[''] = localObject1;
/*      */       
/* 3510 */       ACTION[''] = localObject1;
/* 3511 */       ACTION[''] = localObject1;
/* 3512 */       ACTION[''] = localObject1;
/* 3513 */       ACTION[''] = localObject1;
/* 3514 */       ACTION[''] = localObject1;
/* 3515 */       ACTION[''] = localObject1;
/* 3516 */       ACTION[''] = localObject1;
/* 3517 */       ACTION[' '] = localObject1;
/* 3518 */       ACTION['¡'] = arrayOfInt19;
/*      */       
/* 3520 */       ACTION['¢'] = localObject1;
/* 3521 */       ACTION['£'] = localObject1;
/* 3522 */       ACTION['¤'] = localObject1;
/* 3523 */       ACTION['¥'] = arrayOfInt20;
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3531 */       localObject1 = newArray(128, ODBCAction.NONE);
/*      */       
/* 3533 */       localObject2 = newArray(128, ODBCAction.COPY);
/*      */       
/* 3535 */       localObject3 = copy((ODBCAction[])localObject2);
/* 3536 */       localObject3[123] = ODBCAction.NONE;
/* 3537 */       localObject3[63] = ODBCAction.QUESTION;
/* 3538 */       localObject3[125] = ODBCAction.END_ODBC_ESCAPE;
/* 3539 */       localObject3[44] = ODBCAction.COMMA;
/* 3540 */       localObject3[40] = ODBCAction.OPEN_PAREN;
/* 3541 */       localObject3[41] = ODBCAction.CLOSE_PAREN;
/*      */       
/* 3543 */       localObject4 = copyReplacing((ODBCAction[])localObject2, ODBCAction.COPY, ODBCAction.SAVE_DELIMITER);
/*      */       
/* 3545 */       localObject5 = copyReplacing((ODBCAction[])localObject2, ODBCAction.COPY, ODBCAction.LOOK_FOR_DELIMITER);
/*      */       
/* 3547 */       localObject6 = copy((ODBCAction[])localObject5);
/* 3548 */       localObject6[39] = ODBCAction.COPY;
/*      */       
/* 3550 */       localObject7 = newArray(128, ODBCAction.UNKNOWN_ESCAPE);
/*      */       
/* 3552 */       localObject8 = copy((ODBCAction[])localObject1);
/* 3553 */       for (int m = 0; m < arrayOfInt1.length; m++) {
/* 3554 */         if (arrayOfInt1[m] == 9) { localObject8[m] = ODBCAction.UNKNOWN_ESCAPE;
/*      */         }
/*      */       }
/* 3557 */       ODBCAction[] arrayOfODBCAction = copy((ODBCAction[])localObject3);
/* 3558 */       for (int n = 0; n < arrayOfInt1.length; n++) {
/* 3559 */         if (arrayOfInt1[n] != 9) { arrayOfODBCAction[n] = ODBCAction.BEGIN;
/*      */         }
/*      */       }
/* 3562 */       ODBC_ACTION[0] = localObject3;
/* 3563 */       ODBC_ACTION[1] = localObject3;
/* 3564 */       ODBC_ACTION[2] = localObject3;
/* 3565 */       ODBC_ACTION[3] = localObject2;
/* 3566 */       ODBC_ACTION[4] = localObject3;
/* 3567 */       ODBC_ACTION[5] = localObject2;
/* 3568 */       ODBC_ACTION[6] = localObject2;
/* 3569 */       ODBC_ACTION[7] = localObject2;
/* 3570 */       ODBC_ACTION[8] = localObject3;
/* 3571 */       ODBC_ACTION[''] = localObject3;
/* 3572 */       ODBC_ACTION[100] = localObject2;
/* 3573 */       ODBC_ACTION[101] = localObject2;
/* 3574 */       ODBC_ACTION[105] = localObject2;
/* 3575 */       ODBC_ACTION[106] = localObject4;
/* 3576 */       ODBC_ACTION[107] = localObject5;
/* 3577 */       ODBC_ACTION[108] = null;
/* 3578 */       ODBC_ACTION[109] = localObject6;
/* 3579 */       ODBC_ACTION[9] = localObject3;
/* 3580 */       ODBC_ACTION[10] = localObject3;
/* 3581 */       ODBC_ACTION[11] = localObject3;
/* 3582 */       ODBC_ACTION[12] = localObject3;
/* 3583 */       ODBC_ACTION[13] = localObject3;
/* 3584 */       ODBC_ACTION[14] = arrayOfODBCAction;
/* 3585 */       ODBC_ACTION[15] = localObject3;
/* 3586 */       ODBC_ACTION[16] = localObject3;
/* 3587 */       ODBC_ACTION[17] = localObject3;
/* 3588 */       ODBC_ACTION[18] = localObject3;
/* 3589 */       ODBC_ACTION[19] = localObject3;
/* 3590 */       ODBC_ACTION[20] = localObject3;
/* 3591 */       ODBC_ACTION[21] = localObject3;
/* 3592 */       ODBC_ACTION[22] = localObject3;
/* 3593 */       ODBC_ACTION[23] = localObject3;
/* 3594 */       ODBC_ACTION[24] = localObject3;
/* 3595 */       ODBC_ACTION[25] = localObject3;
/* 3596 */       ODBC_ACTION[26] = localObject3;
/* 3597 */       ODBC_ACTION[27] = localObject3;
/* 3598 */       ODBC_ACTION[28] = localObject3;
/* 3599 */       ODBC_ACTION[29] = localObject3;
/* 3600 */       ODBC_ACTION[30] = localObject3;
/* 3601 */       ODBC_ACTION[31] = localObject3;
/* 3602 */       ODBC_ACTION[32] = localObject3;
/* 3603 */       ODBC_ACTION[33] = localObject3;
/* 3604 */       ODBC_ACTION[34] = localObject3;
/* 3605 */       ODBC_ACTION[35] = localObject3;
/* 3606 */       ODBC_ACTION[36] = localObject3;
/* 3607 */       ODBC_ACTION[37] = localObject3;
/* 3608 */       ODBC_ACTION[38] = localObject3;
/* 3609 */       ODBC_ACTION[39] = localObject3;
/* 3610 */       ODBC_ACTION[40] = localObject3;
/* 3611 */       ODBC_ACTION[41] = localObject3;
/* 3612 */       ODBC_ACTION[42] = localObject3;
/* 3613 */       ODBC_ACTION[43] = localObject3;
/* 3614 */       ODBC_ACTION[44] = localObject3;
/* 3615 */       ODBC_ACTION[45] = localObject3;
/* 3616 */       ODBC_ACTION[46] = localObject3;
/* 3617 */       ODBC_ACTION[47] = localObject3;
/* 3618 */       ODBC_ACTION[48] = localObject3;
/* 3619 */       ODBC_ACTION[49] = localObject3;
/* 3620 */       ODBC_ACTION[50] = localObject3;
/* 3621 */       ODBC_ACTION[51] = localObject3;
/* 3622 */       ODBC_ACTION[52] = localObject3;
/* 3623 */       ODBC_ACTION[53] = localObject3;
/* 3624 */       ODBC_ACTION[54] = localObject3;
/* 3625 */       ODBC_ACTION[55] = localObject3;
/* 3626 */       ODBC_ACTION[56] = localObject3;
/* 3627 */       ODBC_ACTION[66] = localObject3;
/* 3628 */       ODBC_ACTION[58] = localObject3;
/* 3629 */       ODBC_ACTION[59] = localObject3;
/* 3630 */       ODBC_ACTION[60] = localObject2;
/* 3631 */       ODBC_ACTION[61] = localObject2;
/* 3632 */       ODBC_ACTION[62] = localObject2;
/* 3633 */       ODBC_ACTION[63] = localObject2;
/* 3634 */       ODBC_ACTION[64] = localObject2;
/* 3635 */       ODBC_ACTION[65] = localObject3;
/* 3636 */       ODBC_ACTION[102] = localObject2;
/* 3637 */       ODBC_ACTION[103] = localObject2;
/* 3638 */       ODBC_ACTION[104] = localObject2;
/* 3639 */       ODBC_ACTION[110] = localObject2;
/* 3640 */       ODBC_ACTION[111] = localObject4;
/* 3641 */       ODBC_ACTION[112] = localObject5;
/* 3642 */       ODBC_ACTION[113] = null;
/* 3643 */       ODBC_ACTION[114] = localObject6;
/*      */       
/* 3645 */       ODBC_ACTION[57] = localObject3;
/*      */       
/* 3647 */       ODBC_ACTION[67] = localObject3;
/* 3648 */       ODBC_ACTION[68] = localObject3;
/* 3649 */       ODBC_ACTION[69] = localObject3;
/* 3650 */       ODBC_ACTION[70] = localObject3;
/* 3651 */       ODBC_ACTION[71] = localObject3;
/*      */       
/* 3653 */       ODBC_ACTION[72] = localObject3;
/* 3654 */       ODBC_ACTION[73] = localObject3;
/* 3655 */       ODBC_ACTION[74] = localObject3;
/* 3656 */       ODBC_ACTION[75] = localObject3;
/* 3657 */       ODBC_ACTION[76] = localObject3;
/*      */       
/* 3659 */       ODBC_ACTION[77] = localObject3;
/* 3660 */       ODBC_ACTION[78] = localObject3;
/* 3661 */       ODBC_ACTION[79] = localObject3;
/*      */       
/* 3663 */       ODBC_ACTION[80] = localObject3;
/* 3664 */       ODBC_ACTION[81] = localObject3;
/* 3665 */       ODBC_ACTION[82] = localObject3;
/* 3666 */       ODBC_ACTION[83] = localObject3;
/* 3667 */       ODBC_ACTION[84] = localObject3;
/*      */       
/* 3669 */       ODBC_ACTION[85] = localObject3;
/* 3670 */       ODBC_ACTION[86] = localObject3;
/* 3671 */       ODBC_ACTION[87] = localObject3;
/*      */       
/* 3673 */       ODBC_ACTION[88] = localObject3;
/* 3674 */       ODBC_ACTION[89] = localObject3;
/* 3675 */       ODBC_ACTION[90] = localObject3;
/* 3676 */       ODBC_ACTION[91] = localObject3;
/* 3677 */       ODBC_ACTION[92] = localObject3;
/* 3678 */       ODBC_ACTION[93] = localObject3;
/* 3679 */       ODBC_ACTION[94] = localObject3;
/*      */       
/* 3681 */       ODBC_ACTION[95] = localObject3;
/* 3682 */       ODBC_ACTION[96] = localObject3;
/* 3683 */       ODBC_ACTION[97] = localObject3;
/* 3684 */       ODBC_ACTION[98] = localObject3;
/* 3685 */       ODBC_ACTION[99] = localObject3;
/*      */       
/* 3687 */       ODBC_ACTION[115] = copy((ODBCAction[])localObject8);
/* 3688 */       ODBC_ACTION[115][63] = ODBCAction.NONE;
/* 3689 */       ODBC_ACTION[115][99] = ODBCAction.NONE;
/* 3690 */       ODBC_ACTION[115][67] = ODBCAction.NONE;
/* 3691 */       ODBC_ACTION[115][116] = ODBCAction.NONE;
/* 3692 */       ODBC_ACTION[115][84] = ODBCAction.NONE;
/* 3693 */       ODBC_ACTION[115][100] = ODBCAction.NONE;
/* 3694 */       ODBC_ACTION[115][68] = ODBCAction.NONE;
/* 3695 */       ODBC_ACTION[115][101] = ODBCAction.NONE;
/* 3696 */       ODBC_ACTION[115][69] = ODBCAction.NONE;
/* 3697 */       ODBC_ACTION[115][102] = ODBCAction.NONE;
/* 3698 */       ODBC_ACTION[115][70] = ODBCAction.NONE;
/* 3699 */       ODBC_ACTION[115][111] = ODBCAction.NONE;
/* 3700 */       ODBC_ACTION[115][79] = ODBCAction.NONE;
/* 3701 */       ODBC_ACTION[116] = newArray(128, ODBCAction.FUNCTION);
/* 3702 */       ODBC_ACTION[117] = copy((ODBCAction[])localObject7);
/* 3703 */       ODBC_ACTION[117][97] = ODBCAction.NONE;
/* 3704 */       ODBC_ACTION[117][65] = ODBCAction.NONE;
/* 3705 */       ODBC_ACTION[118] = copy((ODBCAction[])localObject7);
/* 3706 */       ODBC_ACTION[118][108] = ODBCAction.NONE;
/* 3707 */       ODBC_ACTION[118][76] = ODBCAction.NONE;
/* 3708 */       ODBC_ACTION[119] = copy((ODBCAction[])localObject7);
/* 3709 */       ODBC_ACTION[119][108] = ODBCAction.NONE;
/* 3710 */       ODBC_ACTION[119][76] = ODBCAction.NONE;
/* 3711 */       ODBC_ACTION[120] = copyReplacing((ODBCAction[])localObject8, ODBCAction.NONE, ODBCAction.CALL);
/* 3712 */       ODBC_ACTION[121] = copyReplacing((ODBCAction[])localObject8, ODBCAction.NONE, ODBCAction.TIME);
/* 3713 */       ODBC_ACTION[121][115] = ODBCAction.NONE;
/* 3714 */       ODBC_ACTION[121][83] = ODBCAction.NONE;
/* 3715 */       ODBC_ACTION[122] = copyReplacing((ODBCAction[])localObject8, ODBCAction.NONE, ODBCAction.TIMESTAMP);
/* 3716 */       ODBC_ACTION[123] = copyReplacing((ODBCAction[])localObject8, ODBCAction.NONE, ODBCAction.DATE);
/* 3717 */       ODBC_ACTION[124] = copy((ODBCAction[])localObject7);
/* 3718 */       ODBC_ACTION[124][115] = ODBCAction.NONE;
/* 3719 */       ODBC_ACTION[124][83] = ODBCAction.NONE;
/* 3720 */       ODBC_ACTION[125] = copy((ODBCAction[])localObject7);
/* 3721 */       ODBC_ACTION[125][99] = ODBCAction.NONE;
/* 3722 */       ODBC_ACTION[125][67] = ODBCAction.NONE;
/* 3723 */       ODBC_ACTION[126] = copy((ODBCAction[])localObject7);
/* 3724 */       ODBC_ACTION[126][97] = ODBCAction.NONE;
/* 3725 */       ODBC_ACTION[126][65] = ODBCAction.NONE;
/* 3726 */       ODBC_ACTION[127] = copy((ODBCAction[])localObject7);
/* 3727 */       ODBC_ACTION[127][112] = ODBCAction.NONE;
/* 3728 */       ODBC_ACTION[127][80] = ODBCAction.NONE;
/* 3729 */       ODBC_ACTION[''] = copy((ODBCAction[])localObject7);
/* 3730 */       ODBC_ACTION[''][101] = ODBCAction.NONE;
/* 3731 */       ODBC_ACTION[''][69] = ODBCAction.NONE;
/* 3732 */       ODBC_ACTION[''] = copyReplacing((ODBCAction[])localObject8, ODBCAction.NONE, ODBCAction.ESCAPE);
/* 3733 */       ODBC_ACTION[''] = copy((ODBCAction[])localObject7);
/* 3734 */       ODBC_ACTION[''][110] = ODBCAction.NONE;
/* 3735 */       ODBC_ACTION[''][78] = ODBCAction.NONE;
/* 3736 */       ODBC_ACTION[''] = copyReplacing((ODBCAction[])localObject8, ODBCAction.NONE, ODBCAction.SCALAR_FUNCTION);
/* 3737 */       ODBC_ACTION[''] = copy((ODBCAction[])localObject7);
/* 3738 */       ODBC_ACTION[''][106] = ODBCAction.NONE;
/* 3739 */       ODBC_ACTION[''][74] = ODBCAction.NONE;
/* 3740 */       ODBC_ACTION[''] = copyReplacing((ODBCAction[])localObject8, ODBCAction.NONE, ODBCAction.OUTER_JOIN);
/*      */       
/* 3742 */       ODBC_ACTION[''] = localObject3;
/* 3743 */       ODBC_ACTION[''] = localObject3;
/* 3744 */       ODBC_ACTION[''] = localObject3;
/* 3745 */       ODBC_ACTION[''] = localObject3;
/* 3746 */       ODBC_ACTION[''] = localObject3;
/*      */       
/* 3748 */       ODBC_ACTION[''] = localObject3;
/* 3749 */       ODBC_ACTION[''] = localObject3;
/* 3750 */       ODBC_ACTION[''] = localObject3;
/* 3751 */       ODBC_ACTION[''] = localObject3;
/* 3752 */       ODBC_ACTION[''] = localObject3;
/* 3753 */       ODBC_ACTION[''] = localObject3;
/* 3754 */       ODBC_ACTION[''] = localObject3;
/* 3755 */       ODBC_ACTION[''] = localObject3;
/*      */       
/* 3757 */       ODBC_ACTION[''] = localObject3;
/* 3758 */       ODBC_ACTION[''] = localObject3;
/* 3759 */       ODBC_ACTION[''] = localObject3;
/* 3760 */       ODBC_ACTION[''] = localObject3;
/* 3761 */       ODBC_ACTION[''] = localObject3;
/*      */       
/* 3763 */       ODBC_ACTION[''] = localObject3;
/* 3764 */       ODBC_ACTION[''] = localObject3;
/* 3765 */       ODBC_ACTION[''] = localObject3;
/* 3766 */       ODBC_ACTION[''] = localObject3;
/* 3767 */       ODBC_ACTION[''] = localObject3;
/* 3768 */       ODBC_ACTION[''] = localObject3;
/* 3769 */       ODBC_ACTION[''] = localObject3;
/* 3770 */       ODBC_ACTION[' '] = localObject3;
/* 3771 */       ODBC_ACTION['¡'] = localObject3;
/*      */       
/* 3773 */       ODBC_ACTION['¢'] = localObject3;
/* 3774 */       ODBC_ACTION['£'] = localObject3;
/* 3775 */       ODBC_ACTION['¤'] = localObject3;
/* 3776 */       ODBC_ACTION['¥'] = localObject3;
/*      */     } catch (Throwable localThrowable) {
/* 3778 */       localThrowable.printStackTrace();
/*      */     }
/*      */   }
/*      */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/OracleSqlReadOnly.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */