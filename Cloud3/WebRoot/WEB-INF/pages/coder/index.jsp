<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../commonjsp/common-top.jsp" %>
  <head>
    <base href="<%=basePath%>">
    <script type="text/javascript" src="${ctx}/click/coder/coderClick.js"></script>
    <link href="${ctx}/css/form/form2.css" rel="stylesheet" type="text/css" />
  </head>  
  
<body>
<div class="tire">
	<div class="right_title"><p>位置：代码生成器</p></div>
	<div class="tableContentDiv">
		<div class="formDiv">
			<form action="${ctx}/coder/createCode.do" method="post" name="coderForm" id="coderForm">
		    	<input type="hidden" name="id"/>
		    	<input type="hidden" name="index" id="index" value="1" style="width: 400px;"/>
		    	<input type="hidden" id="jicListJson" value='${jicListJson}'/>
		    	<input type="hidden" id="vlListJson" value='${vlListJson}'/>
			    <table class="tableHead">
		   			<tr>
		   				<td><img src="${ctx}/images/formTableTitle.png" class="formTable2Title"/>代码生成器 V3.1</td>
		   			</tr>
		   		</table>
			    
			    <table cellpadding="0" cellspacing="0" border="0" class="formTable2">
			    	<tr>
			            <th style="width: 200px;"><font color="red">*</font>作者：</th>
			            <td>
			            	<input name="author" type="text" id="author" autocomplete="off"/>(例：<a href="javascript:defaultAuthor();">cloudwork</a>)
			            </td>	            
			        </tr>
			        	
			        <tr>
			            <th><font color="red">*</font>POJO名字：</th>
			            <td>
			            	<input name="pojo" type="text" id="pojo" autocomplete="off" onkeyup="changeModelTable(this)"/>(例：User)
			            </td>	            
			        </tr>	
			        
			        <tr>
			            <th><font color="red">*</font>包名：</th>
			            <td>
			            	<input name="packageName" type="text" id="packageName" autocomplete="off"
			            		style="width:250px;" onblur="packageName2LowerCase(this)" 
			            		onkeyup="changeNameSpace(this.value)"/>(例：com.cloud.usermanager)
			            </td>	            
			        </tr>
			        
			        <tr>
			            <th><font color="red">*</font>命名空间(模块)：</th>
			            <td>
			            	<input name="nameSpace" type="text" id="nameSpace" autocomplete="off" style="width:250px;"/>(例：usermanager)
			            </td>	            
			        </tr>       
			        
			        <tr>
			            <th><font color="red">*</font>文件生成路径：</th>
			            <td>
			            	<input name="filePath" type="text" id="filePath" autocomplete="off" style="width:250px;"/>(例：e:\codepath\user)
			            </td>	            
			        </tr>
			        
			        <tr>
			            <th><font color="red">*</font>生成模块定时任务：</th>
			            <td>
			            	<input type="radio" name="createJob" value="no" checked="checked"/>否
			            	<input type="radio" name="createJob" value="yes"/>是
			            	&nbsp;
			            	(需要开发人员自己写任务逻辑代码)
			            </td>	            
			        </tr>
			        
			        <tr>
			            <th><font color="red">*</font>生成模块webservice：</th>
			            <td>
			            	<input type="radio" name="createWebService" value="no" checked="checked"/>否
			            	<input type="radio" name="createWebService" value="yes"/>是
			            	&nbsp;
			            	(需要开发人员自己写webservice逻辑代码)
			            </td>	            
			        </tr>
		     	</table>
		     	
		     	<table class="tableHead" style="margin-top: 3px;">
		   			<tr>
		   				<td>属性字段映射(左侧实体类，右侧数据表)</td>
		   			</tr>
		   		</table>
		   		
		     	<table cellpadding="0" cellspacing="0" border="0" class="formTable2">
			        <tr>
			        	<!-- model实体类 -->
			        	<td colspan="2" width="46%" style="padding: 2px;">
			        		<table width="100%" style="margin: 0px;">
			        			<tr>
			        				<th><font color="red">*</font>model实体类：</th>
			        				<td><input type="text" id="modelName" name="modelName" autocomplete="off"/></td>
			        			</tr>
			        			<tr>
			        				<th><font color="red">*</font>描述：</th>
			        				<td><input type="text" id="modelDesc" name="modelDesc" autocomplete="off"/></td>
			        			</tr>
			        			
			        			<tr>
						       		<td colspan="2" style="text-align: center;background-color: #FFF;"><font style="font-size: 15px;font-weight: bold;">属性1信息</font></td>
						        </tr>
			        			<tr>
			        				<th><font color="red">*</font>属性1：</th>
			        				<td>
			        					<input type="text" id="pro1" name="pro1" onkeyup="pro2col(this.value,1)" value="id" autocomplete="off" readonly="readonly" title="不允许修改"/>
			        					<input type="checkbox" class="chx" id="isProperty" name="isProperty" value="yes" checked="checked"/>主键<font color="red">(勾选上为主键)</font>
			        				</td>
			        			</tr>
			        			<tr>
			        				<th><font color="red">*</font>属性描述：</th>
			        				<td><input type="text" id="prodesc1" name="prodesc1" onkeyup="proDesc2colDesc(this.value,1)" value="主键" autocomplete="off" readonly="readonly" title="不允许修改"/></td>
			        			</tr>
			        			<tr>
			        				<th><font color="red">*</font>属性类型：</th>
			        				<td><input type="text" id="proType1" name="proType1" onkeyup="mappingPro2Col(this.value,1)" value="String" autocomplete="off" readonly="readonly" title="不允许修改"/><div id="proTypeDiv1" style="display: inline;"></div></td>
			        			</tr>
			        			<tr>
			        				<th><font color="red">*</font>JSP控件：</th>
			        				<td>
			        					<select id="jspInput1" name="jspInput1" style="width:210px;" onchange="autoValidation(this.value,1)">
			        						<c:forEach items="${jicList}" var="ji">
			        							<option value="${ji.value}" <c:if test="${ji.value == 'hidden'}">selected="selected"</c:if>>${ji.showValue}</option>
			        						</c:forEach>
			        					</select>
			        				</td>
			        			</tr>
			        			<tr>
			        				<th>属性验证串：<br/><font color="red" style="font-weight: normal;">(无须手动录入)</font></th>
			        				<td>
			        					<textarea rows="10" cols="10" id="proValidation1" name="proValidation1"></textarea>
			        				</td>
			        			</tr>			        			
			        			<tr>
			        				<th><font color="red">*</font>列表显示：</th>
			        				<td>		        					
			        					<input type="radio" name="listShow1" value="yes"/>是<input type="radio" name="listShow1" value="no" checked="checked"/>否
			        				</td>
			        			</tr>
			        			<tr>
			        				<th><font color="red">*</font>列内容显示长度：</th>
			        				<td>		        					
			        					<input type="text" name="maxLength1" value="0"/>(0表示任意长度)
			        				</td>
			        			</tr>
			        			<tr>
			        				<th><font color="red">*</font>参与列表查询：</th>
			        				<td>
			        					<input type="radio" name="isQuery1" value="yes" onclick="autoChangeCreateIndex(this.value,1)"/>是<input type="radio" name="isQuery1" value="no" checked="checked" onclick="autoChangeCreateIndex(this.value,1)"/>否
			        				</td>
			        			</tr>
			        			<tbody id="pro_tbody"></tbody>
									        			
			        		</table>
			        	</td>
			        	
			        	<!-- 数据表 -->
			        	<td colspan="2" width="54%" style="padding: 2px;">
			        		<table width="100%" style="margin: 0px;">
			        			<tr>
			        				<th><font color="red">*</font>数据表名：</th>
			        				<td>
			        					<input type="text" id="tableName" name="tableName" autocomplete="off"/>
			        				</td>
			        			</tr>
			        			<tr>
			        				<th>添加字段属性：</th>
			        				<td style="height: 28px;"><input type="button" value="追加字段" class="btn_list" onclick="addProCol()"/></td>
			        			</tr>
			        			
			        			<tr>
						       		<td colspan="2" style="text-align: center;background-color: #FFF;"><font style="font-size: 15px;font-weight: bold;">字段1信息</font></td>
						        </tr>
			        			<tr>
			        				<th><font color="red">*</font>字段1：</th>
			        				<td><input type="text" id="col1" name="col1" value="ID" autocomplete="off" readonly="readonly" title="不允许修改"/></td>
			        			</tr>
			        			<tr>
			        				<th><font color="red">*</font>字段注释：</th>
			        				<td><input type="text" id="coldesc1" name="coldesc1" value="主键" autocomplete="off" readonly="readonly" title="不允许修改"/></td>
			        			</tr>
			        			<tr>
			        				<th><font color="red">*</font>字段类型：</th>
			        				<td>
			        					<input type="text" id="colType1" name="colType1" value="VARCHAR2(36)" onpropertychange="checkIsChar(this.value,1)" autocomplete="off" readonly="readonly" title="不允许修改"/> 
			        					&nbsp;&nbsp;长度：<input type="text" id="colTypeLength1" name="colTypeLength1" value="36" onkeyup="checkIsChar2(1)" style="width:40px;" readonly="readonly" title="不允许修改"/><font color="red">(长度为36)</font>
			        				</td>
			        			</tr>
			        			<tr>
			        				<th>数据验证：<a href="javascript:restValidation(1)" style="font-weight: normal;">重置</a>&nbsp;</th>
			        				<td>
			        					<c:forEach items="${vlList}" var="validation" varStatus="status">
			        						<input type="checkbox" id="validation1_${status.index+1}" onclick="packageValidationStr(1)"
			        							name="validation1" class="chx" value="${validation.value}"/>${validation.name}&nbsp;&nbsp;
			        					</c:forEach>
			        				</td>
			        			</tr>
			        			<tr>
			        				<th>字段验证串：<br/><font color="red" style="font-weight: normal;">(无须手动录入)</font></th>
			        				<td>
			        					<textarea rows="10" cols="10" id="colValidation1" name="colValidation1"></textarea>
			        				</td>
			        			</tr>
			        			<tr>
			        				<th><font color="red">*</font>参与数据导入：</th>
			        				<td>		        					
			        					<input type="radio" name="isImport1" value="yes"/>是<input type="radio" name="isImport1" value="no" checked="checked"/>否
			        				</td>
			        			</tr>
			        			<tr>
			        				<th><font color="red">*</font>参与列表排序：</th>
			        				<td>		        					
			        					<input type="radio" name="isSort1" value="yes"/>是<input type="radio" name="isSort1" value="no" checked="checked"/>否
			        				</td>
			        			</tr>
			        			<tr>
			        				<th>删除字段属性：</th>
			        				<td>
			        					<input type="button" value="删除字段" disabled="disabled" class="btn_list" title="主键不可删除"/>
			        					<input type="button" value="追加字段" class="btn_list" onclick="addProCol()"/>
			        					&nbsp;&nbsp;创建索引：<input type="radio" name="createIndex1" value="yes"/>是 <input type="radio" name="createIndex1" value="no" checked="checked"/>否
			        				</td>
			        			</tr>
			        			<tbody id="col_tbody"></tbody>
			        			
			        		</table>
			        	</td>
			        </tr>
			    </table>
			    
			    <table cellpadding="0" cellspacing="0" border="0" width="100%" style="margin-top: 5px;">
			    	<tr>
			        	<td colspan="2">
			        		<input type="submit" id="sumbit1" value="生成代码" class="btn" onclick="return changeURL(this)"/>
			        		<input type="submit" id="sumbit2" value="保存" class="btn" onclick="return changeURL(this)"/>
			        		<input type="button" value="刷新" class="btn" onclick="refreshPage()"/>
			        	</td>	        	
			        </tr>
			    </table>
			</form>
		</div>
	</div>
</div>    
</body>
<%@ include file="../../../commonjsp/common-bottom.jsp" %>