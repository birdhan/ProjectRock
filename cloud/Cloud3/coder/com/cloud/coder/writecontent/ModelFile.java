package com.cloud.coder.writecontent;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.cloud.base.util.FileUtil;
import com.cloud.base.util.StringUtil;
import com.cloud.coder.model.Coder;
import com.cloud.coder.model.ProMappingCol;


public class ModelFile {

	public static void write(Map paramMap , String filePath) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Coder coder = (Coder)paramMap.get("model");
		List<ProMappingCol> pmcList = (List<ProMappingCol>)paramMap.get("pmcList");	
		
		byte[] c = new byte[2];
        c[0]=0x0d;
        c[1]=0x0a;//用于输入换行符的字节码
        String enter = new String(c);//将该字节码转化为字符串类型--回车
        
        StringBuffer content = new StringBuffer("");
        content.append("package " + coder.getPackageName() + ".model;" + enter + enter);
        
        content.append("import javax.persistence.Column;"+ enter);
        content.append("import javax.persistence.Entity;"+ enter);
        content.append("import javax.persistence.GeneratedValue;"+ enter);
        content.append("import javax.persistence.Id;"+ enter);
        content.append("import javax.persistence.Table;"+ enter);
        content.append("import javax.persistence.Transient;"+ enter+ enter);
        
        content.append("import org.hibernate.annotations.GenericGenerator;"+ enter);
        content.append("import org.hibernate.annotations.Index;"+ enter+ enter);
        
        content.append("import com.cloud.base.annotation.propertydesc.PropertyDesc;"+ enter);
        content.append("import com.cloud.base.annotation.validatepro.ValidatePro;"+ enter + enter);
        
        content.append("/**"+ enter);
        content.append(" * 说明：model实体类，类的属性上都要写上两个注解。"+ enter);
        content.append(" * @PropertyDesc：表示是属性的描述，这个注解是给excel导出使用的。"+ enter);
        content.append(" * @ValidatePro：表示是该属性在excel导入时候的验证json串"+ enter);
        content.append(" * @author " + coder.getAuthor() + enter);
        content.append(" * 代码创建时间：" + sdf.format(new Date()) + enter);
        content.append(" * "+ enter);
        content.append(" * ValidatePro解释："+ enter);
        content.append(" * required: true	--表示必填项，要求true或false"+ enter);
        content.append(" * maxLength:10	   	--表示内容的最大长度，要求是数字"+ enter);
        content.append(" * date:true		--表示是否为日期，要求是true或false"+ enter);
        content.append(" * isInt:true		--表示是否为正整数，要求是大于1的正整数"+ enter);
        content.append(" *"+ enter);
        content.append(" */"+ enter);
        content.append("@Entity"+ enter);
        content.append("//表名"+ enter);
        content.append("@Table(name=\"" + coder.getTableName() + "\")"+ enter);
        
        boolean createIndexFlag = false;											//默认不创建任何索引
        for(ProMappingCol pmc : pmcList) {
        	String createIndex = StringUtil.null2String(pmc.getCreateIndex());		//创建索引列yes表示为创建索引
        	if(createIndex.equals("yes")) {
        		createIndexFlag = true;
        		break;
        	}
        }
        
        if(createIndexFlag) {														//有需要创建索引的字段
        	content.append("//创建索引"+ enter);
        	content.append("@org.hibernate.annotations.Table("+ enter);
        	content.append("	appliesTo=\"" + coder.getTableName() + "\",			//数据表名"+ enter);
        	content.append("	indexes={"+ enter);
        	String indexStrings = "";
        	for(int i=0;i<pmcList.size();i++) {
        		ProMappingCol pmc = pmcList.get(i);
        		String createIndex = pmc.getCreateIndex();								//创建索引列yes表示为创建索引
            	if(createIndex.equals("yes")) {
            		String douhao = ",";
            		if(i == pmcList.size()-1) {
            			douhao = "";
            		}
            		indexStrings += "@Index(name=\"" + coder.getTableName() + "_" + pmc.getCol() + "\",columnNames={\"" + pmc.getCol() + "\"})#,#";
            	}
        	}
        	if(!indexStrings.equals("")) {
        		String[] index_arr = indexStrings.split("#,#");
        		for(int i=0;i<index_arr.length;i++) {
        			String index = index_arr[i];
        			if(i != index_arr.length-1) {
        				content.append("		" + index + "," + enter);
        			} else {
        				content.append("		" + index + enter);
        			}       			
        		}
        	}
        	content.append("	}"+ enter);
        	content.append(")"+ enter);
        }
        
        content.append("public class "+coder.getModelName()+" {"+ enter+ enter);
      
        for(int i=0;i<pmcList.size();i++) {
        	ProMappingCol pmc = pmcList.get(i);
        	content.append("	/**"+ enter);
			content.append("	 * " + pmc.getProdesc() + enter);
			content.append("	 */" + enter);
        	if(i == 0) {										//判断是否为主键
        		if(coder.getIsProperty().equals("yes")) {		//表示为主键
        			content.append("	@Id"+ enter);
        			content.append("	@GeneratedValue(generator=\"uuid\")"+ enter);
        			content.append("	@GenericGenerator(name = \"uuid\", strategy = \"org.hibernate.id.UUIDGenerator\")"+ enter);
        			content.append("	@PropertyDesc(name=\"" + pmc.getProdesc() + "\")"+ enter);
        			content.append("	@Column(name=\"ID\",columnDefinition=\"VARCHAR(36)\")"+ enter);
        		} else {
        			content.append("	@PropertyDesc(name=\"" + pmc.getProdesc() + "\")"+ enter);
        			if(!StringUtil.null2String(pmc.getColValidation()).equals("")) {
        				content.append("	@ValidatePro(validate=\"{" + pmc.getColValidation() + ",maxlength:'"+pmc.getColTypeLength()+"'}\")"+ enter);
        			} else {
        				content.append("	@ValidatePro(validate=\"{required:'false'}\")"+ enter);
        			}
        			
        			content.append("	@Column(name=\""+pmc.getCol()+"\",columnDefinition=\""+pmc.getColType()+"\")"+ enter);
        		}
        	} else {											//其它属性
        		content.append("	@PropertyDesc(name=\"" + pmc.getProdesc() + "\")"+ enter);
    			if(!StringUtil.null2String(pmc.getColValidation()).equals("")) {
    				content.append("	@ValidatePro(validate=\"{" + pmc.getColValidation() + ",maxlength:'"+pmc.getColTypeLength()+"'}\")"+ enter);
    			} else {
    				content.append("	@ValidatePro(validate=\"{required:'false'}\")"+ enter);
    			}
    			
    			content.append("	@Column(name=\""+pmc.getCol()+"\",columnDefinition=\""+pmc.getColType()+"\")"+ enter);
        	}
        	
        	content.append("	private " + pmc.getProType() + " " + pmc.getPro() + ";"+ enter+ enter);
        	content.append("	public " + pmc.getProType() + " get"+StringUtil.replaceFirstStr2UpperCase(pmc.getPro())+"() {"+ enter);
        	content.append("		return "+pmc.getPro()+";" + enter);
        	content.append("	}" + enter+ enter);
        	
        	content.append("	public void set"+StringUtil.replaceFirstStr2UpperCase(pmc.getPro())+"(" + pmc.getProType() + " "+ pmc.getPro() +"){" + enter);
        	content.append("		this." + pmc.getPro() + " = " + pmc.getPro() + ";"+enter);
        	content.append("	}"+enter);
        	
        	content.append(enter);
        }
        content.append("}"+enter);
        	
        FileUtil.writeFileByContent(filePath, content.toString());
	}
}
