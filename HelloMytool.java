package mytooljenkins.matlabjnk;

@SuppressWarnings("rawtypes")
public class HelloMytool  extends Builder implements SimpleBuildStep{

	
	private final String localmytool;
	
	private final TypeList runTestTypes;
	
	private static String WORK_SPACE = "WORKSPACE";
	private static String customizedFields ;
    
	@DataBoundConstructor
	public Hellomytool(String localmytool,TypeList runTestTypes)
	{
		
		this.localmytool = localmytool;
				
		this.runTestTypes = runTestTypes;
	
		
	}

	public String getLocalmytool(){

		return this.localmytool;
	}
		
	
	 public TypeList getRunTestTypes(){
			
			return this.runTestTypes;
     }
	
	
	@Extension
	public static class Descriptorr extends BuildStepDescriptor<Builder>  {
		
		
		@Override
		public boolean isApplicable(@SuppressWarnings("rawtypes") Class<? extends AbstractProject> jobType) {
			//return mytoolItemProject.class.isAssignableFrom(jobType);
		
			return true;
		}
			 
		
		public boolean isMatrix(AbstractBuild<?, ?> build){ 
		     return "hudson.matrix.MatrixBuild".equals(build.getClass().getName()); 
		} 
		
		public DescriptorExtensionList <TypeList,Descriptor<TypeList>> getTypetDescriptor() {
	        return Jenkins.getInstance().getDescriptorList(TypeList.class);
	    }


		
		//Overridden Method used to show the text under build dropdown
		@Override
		public String getDisplayName() {
			return "Run mytool Tests";
		}



		@Override
		public boolean configure(StaplerRequest req, JSONObject formData) throws FormException {
						
			save();
			return super.configure(req,formData);
		}
		
		
		public FormValidation doCheckLocalmytool(@QueryParameter String localmytool){
			 
			 
			  return FormValidation.ok();
		  }
		
		
		 
		public FormValidation doCheckCob(@QueryParameter boolean cob,
				@QueryParameter String localmytool) {

			
			return FormValidation.ok();
		}



		
	}
	
	/*------------------------------------Descriptor Section -------------------*/
	
	
	 	
	 public static abstract class TypeList implements ExtensionPoint, Describable<TypeList> {
	        protected String name;
	        protected TypeList(String name) { this.name = name; }

	       
			public Descriptor<TypeList> getDescriptor() {
	            return Jenkins.getInstance().getDescriptor(getClass());
	        }
	    }
	 public static abstract class TypetDescriptor extends Descriptor<TypeList> {
				 
	 }

	 
	public static class Auto extends TypeList {
		private boolean runAutomatic;
		private final boolean junit;
		private final boolean cob;

		private final boolean tap;
		@DataBoundConstructor
		public Auto(boolean runAutomatic, boolean tap,boolean junit, boolean cob) {
			super("Auto");
			this.runAutomatic = runAutomatic;
			this.tap = tap;
			this.junit = junit;
			this.cob = cob;
		}

		
		public boolean getTap(){
		  
		 return this.tap; 
		 }
		 
		public boolean getJunit() {

			return this.junit;
		}

		public boolean getCob() {

			return this.cob;
		}

		@Extension
		public static final class DescriptorImpl extends TypetDescriptor {
			public boolean tap;

			@Override
			public String getDisplayName() {
				
				return "Auto";
			}

		}
	}

	 
	public static class Custom extends TypeList {
		private String runCustom;

		@DataBoundConstructor
		public Custom(String runAutomatic) {
			super("Custom");
			this.runCustom = runAutomatic;
		}

		@Extension
		public static final class DescriptorImpl extends TypetDescriptor {

			@Override
			public String getDisplayName() {
				// TODO Auto-generated method stub
				return "Custom";
			}
		}
	}



	@Override
	public void perform(@Nonnull Run<?, ?> build,
			@Nonnull FilePath workspace, @Nonnull Launcher launcher,
			@Nonnull TaskListener listener) throws InterruptedException,
			IOException {
		boolean isError = false;
		//String mytoolRoot = this.localmytool+File.separator+"bin"+File.separator+"mytool";
		String mytoolroot;
		
	}
}
