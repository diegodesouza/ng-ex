<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<!--BEGIN FORM SECTION J-->
				<table BORDER="1" CELLSPACING=0 CELLPADDING=7 WIDTH="100%">
					<tr>
						<td valign="top" align="center" colspan="2" BGCOLOR="#000080"><font size="2" COLOR="#ffffff"><b><%=Constants.sectionExpertise%> - Providers' Self-Reported Areas of Expertise (Note:
						For Behavioral Health Providers Only)</b></font></td>
					</tr>
					
					<%	Iterator theDataJ = errorMessagesVectorJ.iterator();
							if (!errorMessagesVectorJ.isEmpty())
							{%>
								<tr>
									<td valign="top" align="center" colspan="2" BGCOLOR="#FF0000"><font size="2" COLOR="#ffffff"><b>Error Messages</b></font></td>
								</tr>
								<tr>
									<td colspan="2" BGCOLOR="#ffffff"><font size="2" COLOR="#000080"><b>The following errors were detected.  Please correct the form entries and resubmit form.</b></font></td>
								</tr>
							<%	while(theDataJ.hasNext())
								{
									String value = (String)theDataJ.next(); %>
									<tr>
										<TD colspan="2" style="FONT-SIZE: large;"><font size="2" COLOR="#FF0000"><b><%=value%></b></font></TD>
									</tr>
							<%	}  %>
						<%	} %>
						
							
					<tr>
						<td colspan="2">
							<table width="100%">							
								<tr>
									<TD><input type="checkbox" name=add value=""   <%if(expertiseList.isAdd()){%>CHECKED <%}%>>ADD/ADHD</TD>
					                <TD><input type="checkbox" name=divorce value=""   <%if(expertiseList.isDivorce()){%>CHECKED <%}%>>Divorce/Blended Family Issues</TD>    											
					                <TD  rowspan="2"><input type="checkbox" name=neuropsychological value=""   <%if(expertiseList.isNeuropsychological()){%>CHECKED <%}%>>Neuropsychological Testing (*Only those providers who are contracted and allowed to test in your state)</TD>
								</tr>
								<tr>
									<TD><input type="checkbox" name=adoption value=""   <%if(expertiseList.isAdoption()){%>CHECKED <%}%>>Adoption</TD>
					                <TD><input type="checkbox" name=domViolence value=""   <%if(expertiseList.isDomViolence()){%>CHECKED <%}%>>Domestic Violence</TD>    											
					                
								</tr>
								<tr>
									<TD><input type="checkbox" name=anxiety value=""   <%if(expertiseList.isAnxiety()){%>CHECKED <%}%>>Anxiety and Panic Disorders</TD>
					                <TD><input type="checkbox" name=eatingDisorder value=""   <%if(expertiseList.isEatingDisorder()){%>CHECKED <%}%>>Eating Disorders</TD>    											
					                <TD><input type="checkbox" name=obsessiveComp value=""   <%if(expertiseList.isObsessiveComp()){%>CHECKED <%}%>>Obsessive Compulsive Disorder</TD>
								</tr>
								<tr>
									<TD rowspan="2"><input type="checkbox" name=autism value=""   <%if(expertiseList.isAutism()){%>CHECKED <%}%>>Autism/PDD/Asperger's (WI Providers see <%=Constants.sectionWisconsin%>)</TD>
					                <TD><input type="checkbox" name=electroconvulsive value=""   <%if(expertiseList.isElectroconvulsive()){%>CHECKED <%}%>>Electroconvulsive Therapy</TD>    											
					                <TD><input type="checkbox" name=painMgmt value=""   <%if(expertiseList.isPainMgmt()){%>CHECKED <%}%>>Pain Management</TD>
								</tr>
								<tr>
					                <TD><input type="checkbox" name=endofLifeIssues value=""   <%if(expertiseList.isEndofLifeIssues()){%>CHECKED <%}%>>End of Life Issues</TD>    											
					                <TD><input type="checkbox" name=personalityDis value=""   <%if(expertiseList.isPersonalityDis()){%>CHECKED <%}%>>Personality Disorders</TD>
								</tr>
								<tr>
									<TD><input type="checkbox" name=bariatric value=""   <%if(expertiseList.isBariatric()){%>CHECKED <%}%>>Bariatric Surgery</TD>
					                <TD><input type="checkbox" name=familyTherapy value=""   <%if(expertiseList.isFamilyTherapy()){%>CHECKED <%}%>>Family Therapy</TD>    											
					                <TD><input type="checkbox" name=postpartum value=""   <%if(expertiseList.isPostpartum()){%>CHECKED <%}%>>Postpartum Issues</TD>
								</tr>
								<tr>
									<TD><input type="checkbox" name=behaviorMod value=""   <%if(expertiseList.isBehaviorMod()){%>CHECKED <%}%>>Behavior Modification</TD>
					                <TD><input type="checkbox" name=gayIssues value=""   <%if(expertiseList.isGayIssues()){%>CHECKED <%}%>>Gay/Lesbian Issues</TD>    											
					                <TD><input type="checkbox" name=PTSD value=""   <%if(expertiseList.isPTSD()){%>CHECKED <%}%>>Post Traumatic Stress Disorder (PTSD)</TD>
								</tr>
								<tr>
									<TD><input type="checkbox" name=bipolar value=""   <%if(expertiseList.isBipolar()){%>CHECKED <%}%>>Bipolar</TD>
					                <TD><input type="checkbox" name=groupTherapy value=""   <%if(expertiseList.isGroupTherapy()){%>CHECKED <%}%>>Group Therapy </TD>    											
					                <TD><input type="checkbox" name=prenatalIssues value=""   <%if(expertiseList.isPrenatalIssues()){%>CHECKED <%}%>>Prenatal Issues</TD>
								</tr>
								<tr>
									<TD><input type="checkbox" name=briefSolution value=""   <%if(expertiseList.isBriefSolution()){%>CHECKED <%}%>>Brief Solution Focused</TD>
					                <TD><input type="checkbox" name=HIVRelatedIssues value=""   <%if(expertiseList.isHIVRelatedIssues()){%>CHECKED <%}%>>HIV/AIDS Related Issues</TD>    											
					                <TD rowspan="2"><input type="checkbox" name=psychologicalTest value=""   <%if(expertiseList.isPsychologicalTest()){%>CHECKED <%}%>>Psychological Testing (*Only those providers who are contracted and allowed to test in your state)</TD>
								</tr>
								<tr>
									<TD><input type="checkbox" name=chemicalDep value=""   <%if(expertiseList.isChemicalDep()){%>CHECKED <%}%>>Chemical Dependency</TD>
					                <TD><input type="checkbox" name=infertility value=""   <%if(expertiseList.isInfertility()){%>CHECKED <%}%>>Infertility</TD>    											
					                
								</tr>
								<tr>
									<TD><input type="checkbox" name=chemicalDepAssessment value=""   <%if(expertiseList.isChemicalDepAssessment()){%>CHECKED <%}%>>Chemical Dependency Assessment</TD>
					                <TD><input type="checkbox" name=counselOpioid value=""   <%if(expertiseList.isCounselOpioid()){%>CHECKED <%}%>>MAT Counseling for Opioid Use Disorders</TD>    											
					                <TD><input type="checkbox" name=schizophrenia value=""   <%if(expertiseList.isSchizophrenia()){%>CHECKED <%}%>>Schizophrenia/Schizoaffective Disorder</TD>
								</tr>
								<tr>
									<TD><input type="checkbox" name=christianCounseling value=""   <%if(expertiseList.isChristianCounseling()){%>CHECKED <%}%>>Christian Counseling </TD>
					                <TD><input type="checkbox" name=matOpioid value=""   <%if(expertiseList.isMatOpioid()){%>CHECKED <%}%>>MAT for Opioid Use Disorders</TD>    											
					                <TD><input type="checkbox" name=sexualAbuse value=""   <%if(expertiseList.isSexualAbuse()){%>CHECKED <%}%>>Sexual Abuse </TD>
								</tr>
								<tr>
									<TD><input type="checkbox" name=compulsiveGambling value=""   <%if(expertiseList.isCompulsiveGambling()){%>CHECKED <%}%>>Compulsive Gambling</TD>
					                <TD><input type="checkbox" name=matWaivered value=""   <%if(expertiseList.isMatWaivered()){%>CHECKED <%}%>>MAT Waivered Prescriber</TD>
					                <TD><input type="checkbox" name=sexualDisorder value=""   <%if(expertiseList.isSexualDisorder()){%>CHECKED <%}%>>Sexual Disorders </TD>
								</tr>
								<tr>
									<TD><input type="checkbox" name=ethnicIssues value=""   <%if(expertiseList.isEthnicIssues()){%>CHECKED <%}%>>Cultural/Ethnic Issues</TD>
					                <TD rowspan="2"><input type="checkbox" name=medicationMgmt value=""   <%if(expertiseList.isMedicationMgmt()){%>CHECKED <%}%>>Medication Management  (*Only those providers who are contracted and allowed to prescribe in your state)</TD>   											
					                <TD><input type="checkbox" name=telehealthProvAOE value=""   <%if(expertiseList.isTelehealthProv()){%>CHECKED <%}%>>Telehealth Provider</TD>
								</tr>
								<tr>
									<TD><input type="checkbox" name=depressiveDisorder value=""   <%if(expertiseList.isDepressiveDisorder()){%>CHECKED <%}%>>Depressive Disorders </TD>  											
					                
					                <TD><input type="checkbox" name=victims value=""   <%if(expertiseList.isVictims()){%>CHECKED <%}%>>Victims of Abuse, Assault, Trauma</TD>
								</tr>
								<tr>
									<TD><input type="checkbox" name=dialectical value=""   <%if(expertiseList.isDialectical()){%>CHECKED <%}%>>Dialectical Behavioral Therapy</TD>
					                <TD><input type="checkbox" name=menIssues value=""   <%if(expertiseList.isMenIssues()){%>CHECKED <%}%>>Men Issues </TD>
					                <TD><input type="checkbox" name=womenIssues  value=""   <%if(expertiseList.isWomenIssues()){%>CHECKED <%}%>>Women Issues </TD>    											
					                <TD></TD>
								</tr>
								
							</table>
						
						</td>
					</tr>
				</table>
</body>
</html>