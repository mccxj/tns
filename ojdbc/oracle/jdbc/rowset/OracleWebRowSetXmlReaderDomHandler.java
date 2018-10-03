package oracle.jdbc.rowset;
import javax.sql.RowSet;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;
class OracleWebRowSetXmlReaderDomHandler
  extends OracleWebRowSetXmlReaderContHandler
{
  OracleWebRowSetXmlReaderDomHandler(RowSet paramRowSet)
  {
/*  42 */     super(paramRowSet);
  }
  
  void readXMLDocument(Document paramDocument)
    throws SAXException
  {
/*  50 */     Element localElement = paramDocument.getDocumentElement();
/*  51 */     startElement(null, null, "webRowSet", null);
    
/*  54 */     Node localNode1 = localElement.getElementsByTagName("properties").item(0);
    
/*  56 */     startElement(null, null, "properties", null);
    
/*  59 */     NodeList localNodeList1 = localNode1.getChildNodes();
/*  60 */     int i = localNodeList1.getLength();
    
/*  65 */     for (int j = 0; j < i; j++)
    {
/*  67 */       Node localNode2 = localNodeList1.item(j);
      
/*  70 */       if (!(localNode2 instanceof Text))
      {
/*  74 */         String str1 = localNode2.getNodeName();
/*  75 */         startElement(null, null, str1, null);
        
/*  79 */         if (localNode2.hasChildNodes())
        {
/*  81 */           processElement(localNode2.getFirstChild().getNodeValue());
        }
        else
        {
/*  88 */           processElement("");
        }
        
/*  91 */         endElement(null, null, str1);
      }
    }
/*  94 */     endElement(null, null, "properties");
    
/*  97 */     Node localNode3 = localElement.getElementsByTagName("metadata").item(0);
    
/*  99 */     startElement(null, null, "metadata", null);
    
/* 102 */     Node localNode4 = localNode3.getFirstChild().getNextSibling();
    
/* 104 */     String str2 = localNode4.getNodeName();
/* 105 */     startElement(null, null, str2, null);
    
/* 108 */     processElement(localNode4.getFirstChild().getNodeValue());
/* 109 */     endElement(null, null, str2);
    
/* 112 */     NodeList localNodeList2 = localNode3.getChildNodes();
/* 113 */     int k = localNodeList2.getLength();
    Object localObject2;
    Object localObject3;
/* 116 */     for (int m = 3; m < k; m++)
    {
/* 118 */       localObject1 = localNodeList2.item(m);
      
/* 121 */       NodeList localNodeList3 = ((Node)localObject1).getChildNodes();
/* 122 */       i1 = localNodeList3.getLength();
      
/* 124 */       for (int i2 = 0; i2 < i1; i2++)
      {
/* 126 */         localObject2 = localNodeList3.item(i2);
        
/* 129 */         if (!(localObject2 instanceof Text))
        {
/* 133 */           localObject3 = ((Node)localObject2).getNodeName();
/* 134 */           startElement(null, null, (String)localObject3, null);
          
/* 138 */           if (((Node)localObject2).hasChildNodes())
          {
/* 140 */             processElement(((Node)localObject2).getFirstChild().getNodeValue());
          }
          else
          {
/* 147 */             processElement("");
          }
          
/* 150 */           endElement(null, null, (String)localObject3);
        }
      }
    }
/* 154 */     endElement(null, null, "metadata");
    
/* 157 */     Node localNode5 = localElement.getElementsByTagName("data").item(0);
/* 158 */     startElement(null, null, "data", null);
    
/* 161 */     Object localObject1 = localNode5.getChildNodes();
/* 162 */     int n = ((NodeList)localObject1).getLength();
    
/* 164 */     for (int i1 = 0; i1 < n; i1++)
    {
/* 166 */       Node localNode6 = ((NodeList)localObject1).item(i1);
      
/* 169 */       if (!(localNode6 instanceof Text))
      {
/* 173 */         localObject2 = localNode6.getNodeName();
/* 174 */         startElement(null, null, (String)localObject2, null);
        
/* 177 */         localObject3 = localNode6.getChildNodes();
/* 178 */         int i3 = ((NodeList)localObject3).getLength();
        
/* 180 */         for (int i4 = 0; i4 < i3; i4++)
        {
/* 182 */           Node localNode7 = ((NodeList)localObject3).item(i4);
          
/* 185 */           if (!(localNode7 instanceof Text))
          {
/* 189 */             String str3 = localNode7.getNodeName();
/* 190 */             startElement(null, null, str3, null);
            
            String str4;
            
/* 195 */             if (localNode7.hasChildNodes())
            {
/* 197 */               str4 = localNode7.getFirstChild().getNodeValue();
/* 198 */               if (str4 == null)
              {
/* 200 */                 startElement(null, null, "null", null);
              }
              
            }
            else
            {
/* 207 */               str4 = "";
            }
            
/* 210 */             processElement(str4);
            
/* 212 */             endElement(null, null, str3);
          }
        }
        
/* 217 */         endElement(null, null, (String)localObject2);
      }
    }
    
/* 221 */     endElement(null, null, "data");
    
/* 223 */     endElement(null, null, "webRowSet");
    
/* 225 */     endDocument();
  }
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/rowset/OracleWebRowSetXmlReaderDomHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */