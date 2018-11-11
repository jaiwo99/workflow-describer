# Workflow Describer

## Mission

* Show workflows with belonging instances
* Show workflows with running instances
* Show Contractors

## Get started

### Build Project
```bash
./gradlew clean build 
```

### Run Project
```bash
java -jar build/lib/*.jar help
```

## Usage

```
#######################################################################################
# 
# Usage:
# 
# java -jar <app.jar> <OPTION>
# 
# Current supported OPTIONs are:
# 
#       - help
#       - show-contractors
#       - show-workflows-with-instance
#       - show-workflows-with-running-instance
# 
# Default data locations:
# 
#       - contractor data:                      /tmp/contractors.data
#       - workflow data:                        /tmp/workflows.data
#       - workflow instance data:       /tmp/workflowInstances.data
# 
# You can override data location by using parameter:
# 
#       - --app.workflow-path=<PATH>
#       - --app.workflow-instance-path=<PATH>
#       - --app.contractor-path=<PATH>
# 
# Example:
# 
#       java -jar <app.jar> <OPTION> --app.workflow-path=<PATH>
# 
#######################################################################################

```

## Debugging

Application log can be found in `app.log`