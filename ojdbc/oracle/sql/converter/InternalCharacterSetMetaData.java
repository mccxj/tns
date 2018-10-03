package oracle.sql.converter;
abstract interface InternalCharacterSetMetaData
{
  public abstract boolean isFixedWidth(int paramInt);
  
  public abstract int getMaxCharLength(int paramInt);
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/sql/converter/InternalCharacterSetMetaData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */