package rocks.inspectit.agent.java.sensor.method;

import java.util.Map;

import org.slf4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import rocks.inspectit.agent.java.config.IConfigurationStorage;
import rocks.inspectit.shared.all.instrumentation.config.impl.AbstractSensorTypeConfig;
import rocks.inspectit.shared.all.instrumentation.config.impl.MethodSensorTypeConfig;
import rocks.inspectit.shared.all.spring.logger.Log;

/**
 * Abstract class for all {@link IMethodSensor}s to properly initialize after Spring has set all the
 * properties.
 *
 * @author Ivan Senic
 *
 */
public abstract class AbstractMethodSensor implements IMethodSensor, InitializingBean {

	/**
	 * The logger of the class.
	 */
	@Log
	Logger log;

	/**
	 * Configuration storage for initializing the sensor and registering with the config.
	 */
	@Autowired
	private IConfigurationStorage configurationStorage;

	/**
	 * Sensor type configuration used.
	 */
	private MethodSensorTypeConfig sensorTypeConfig;

	/**
	 * Called when hook should be initialized.
	 *
	 * @param parameters
	 *            Parameters passed via the {@link AbstractSensorTypeConfig}.
	 */
	protected abstract void initHook(Map<String, Object> parameters);

	/**
	 * {@inheritDoc}
	 */
	public void init(MethodSensorTypeConfig sensorTypeConfig) {
		this.sensorTypeConfig = sensorTypeConfig;

		initHook(sensorTypeConfig.getParameters());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public MethodSensorTypeConfig getSensorTypeConfig() {
		return sensorTypeConfig;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		for (MethodSensorTypeConfig config : configurationStorage.getMethodSensorTypes()) {
			if (config.getClassName().equals(this.getClass().getName())) {
				this.init(config);
				if (log.isInfoEnabled()) {
					log.info("Method sensor has been initialized.");
				}
				break;
			}
		}
	}

}
