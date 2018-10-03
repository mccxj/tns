package oracle.sql;
import java.util.HashMap;
class TableClass
{
  private HashMap<String, Integer> zoneToIdMap;
  private HashMap<Integer, String> idToZoneMap;
  private HashMap<Integer, String> oldIdToZoneMap;
  
  TableClass(int paramInt, float paramFloat)
  {
/* 764 */     this.zoneToIdMap = new HashMap(paramInt, paramFloat);
/* 765 */     this.idToZoneMap = new HashMap(paramInt, paramFloat);
    
/* 767 */     this.oldIdToZoneMap = new HashMap(10, 0.99F);
  }
  
  void put(String paramString, Integer paramInteger)
  {
/* 772 */     this.zoneToIdMap.put(paramString, paramInteger);
/* 773 */     this.idToZoneMap.put(paramInteger, paramString);
  }
  
  void putOld(String paramString, Integer paramInteger)
  {
/* 778 */     this.oldIdToZoneMap.put(paramInteger, paramString);
  }
  
  Integer getID(String paramString)
  {
/* 783 */     return (Integer)this.zoneToIdMap.get(paramString);
  }
  
  String getZone(Integer paramInteger)
  {
/* 788 */     return (String)this.idToZoneMap.get(paramInteger);
  }
  
  String getOldZone(Integer paramInteger)
  {
/* 793 */     return (String)this.oldIdToZoneMap.get(paramInteger);
  }
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/sql/TableClass.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */