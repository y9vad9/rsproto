package org.timemates.rsp.exceptions

import org.timemates.rsp.metadata.ClientMetadata

public class ProcedureNotFoundException(
    metadata: ClientMetadata
) : Exception("Procedure '${metadata.procedureName}' is not found in service named '${metadata.serviceName}'")