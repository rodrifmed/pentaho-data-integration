package com.rodrifmed.lib.dataintegration.transformation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.RowSet;
import org.pentaho.di.core.database.DatabaseMeta;
import org.pentaho.di.core.util.EnvUtil;
import org.pentaho.di.trans.Trans;
import org.pentaho.di.trans.TransMeta;
import org.pentaho.di.trans.step.StepMetaDataCombi;

import com.google.gson.Gson;
import com.rodrifmed.lib.dataintegration.dto.StepDTO;

public class TransformationManager {
	private String dbHostName;
	private String dbUerName;
	private String dbPassword;
	private String dbPort;
	private String dbName;
	private String connectionName;
	private List<StepDTO> stepDTOList;
	private Map<String, String> parameters;

	public TransformationManager() {
	}

	public Map<String, Object> executeTransformation(String ktrPath) {

		Map<String, Object> executionResult = new HashMap<String, Object>();

		try {
			KettleEnvironment.init();
			EnvUtil.environmentInit();

			TransMeta transMeta = new TransMeta(ktrPath);

			List<DatabaseMeta> dbMetaList = transMeta.getDatabases();

			for (DatabaseMeta dbMeta : dbMetaList) {
				if (dbMeta.getName().equals(this.connectionName)) {
					dbMeta.setHostname(this.dbHostName);
					dbMeta.setUsername(this.dbUerName);
					dbMeta.setPassword(this.dbPassword);
					dbMeta.setDBPort(this.dbPort);
					dbMeta.setDBName(this.dbName);
				}
			}

			Trans transformation = new Trans(transMeta);

			if (this.parameters != null) {
				for (Map.Entry<String, String> entry : this.parameters.entrySet()) {
					transformation.setParameterValue((String) entry.getKey(), (String) entry.getValue());
				}
			}

			transformation.execute(null);
			transformation.waitUntilFinished();

			for (StepMetaDataCombi combi : transformation.getSteps()) {

				StepDTO stepDTO = new StepDTO();

				stepDTO.setStepName(combi.step.getStepname());
				stepDTO.setLinesInput(Long.valueOf(combi.step.getLinesInput()));
				stepDTO.setLinesOutput(Long.valueOf(combi.step.getLinesOutput()));
				stepDTO.setLinesRead(Long.valueOf(combi.step.getLinesRead()));
				stepDTO.setLinesRejected(Long.valueOf(combi.step.getLinesRejected()));
				stepDTO.setLinesUpdated(Long.valueOf(combi.step.getLinesUpdated()));

				stepDTO.setStepDestinationNameList(new ArrayList<String>());

				for (RowSet rowSet : combi.step.getOutputRowSets()) {
					stepDTO.getStepDestinationNameList().add(rowSet.getDestinationStepName());
				}

				this.getStepDTOList().add(stepDTO);
			}

			if (transformation.getErrors() > 0) {
				System.out.println("Erroruting Transformation");

				executionResult.put("transformationExecuted", false);
				return executionResult;
			} else {

				executionResult.put("transformationExecuted", true);
				return executionResult;
			}
		} catch (Exception e) {
			e.printStackTrace();

			executionResult.put("transformationExecuted", false);
			return executionResult;
		}
	}

	public String getStepDTOListAsJson() {
		Gson gson = new Gson();
		return gson.toJson(this.getStepDTOList());
	}

	public List<StepDTO> getStepDTOList() {
		if (this.stepDTOList == null) {
			this.stepDTOList = new ArrayList<StepDTO>();
		}

		return this.stepDTOList;
	}

	public Map<String, String> getParameters() {
		return this.parameters;
	}

	public void setParameters(Map<String, String> parameters) {
		this.parameters = parameters;
	}

	public String getDbHostName() {
		return this.dbHostName;
	}

	public void setDbHostName(String dbHostName) {
		this.dbHostName = dbHostName;
	}

	public String getDbUerName() {
		return this.dbUerName;
	}

	public void setDbUerName(String dbUerName) {
		this.dbUerName = dbUerName;
	}

	public String getDbPort() {
		return this.dbPort;
	}

	public void setDbPort(String dbPort) {
		this.dbPort = dbPort;
	}

	public String getDbName() {
		return this.dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String getDbPassword() {
		return this.dbPassword;
	}

	public void setDbPassword(String dbPassword) {
		this.dbPassword = dbPassword;
	}

	public String getConnectionName() {
		return this.connectionName;
	}

	public void setConnectionName(String connectionName) {
		this.connectionName = connectionName;
	}
}
