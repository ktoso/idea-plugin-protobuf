package protobuf.compiler;

import com.intellij.openapi.compiler.CompilerManager;
import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import protobuf.file.ProtobufFileType;

/**
 * author: Nikolay Matveev
 * Date: Apr 5, 2010
 */
public class PbCompilerLoader implements ProjectComponent {

    Project myProject;

    public PbCompilerLoader(Project project) {
        myProject = project;
    }

    @Override
    public void projectOpened() {
        PbCompilerApplicationSettings appSettings = PbCompilerApplicationSettings.getInstance();
        PbCompilerProjectSettings projectSettings = PbCompilerProjectSettings.getInstance(myProject);
        if(projectSettings.COMPILE_PROTO){
            CompilerManager compilerManager = CompilerManager.getInstance(myProject);
            compilerManager.addCompilableFileType(ProtobufFileType.PROTOBUF_FILE_TYPE);
            CompilerManager.getInstance(myProject).addCompiler(new PbCompiler(myProject));
        }
        if(projectSettings.OUTPUT_SOURCE_DIRECTORY.length() == 0){
            //load default value
        }
    }

    @Override
    public void projectClosed() {
    }

    @NotNull
    @Override
    public String getComponentName() {
        return "PbCompilerLoader";
    }

    @Override
    public void initComponent() {        
    }

    @Override
    public void disposeComponent() {
    }
}
