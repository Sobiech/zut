﻿//------------------------------------------------------------------------------
// <auto-generated>
//     Ten kod został wygenerowany przez narzędzie.
//     Wersja wykonawcza:4.0.30319.42000
//
//     Zmiany w tym pliku mogą spowodować nieprawidłowe zachowanie i zostaną utracone, jeśli
//     kod zostanie ponownie wygenerowany.
// </auto-generated>
//------------------------------------------------------------------------------

namespace BooksClient.BookManagerService {
    
    
    /// <remarks/>
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Xml", "4.7.2612.0")]
    [System.SerializableAttribute()]
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.ComponentModel.DesignerCategoryAttribute("code")]
    [System.Xml.Serialization.XmlTypeAttribute(Namespace="http://manager.ia.zut.locon.pl/")]
    public partial class Exception : object, System.ComponentModel.INotifyPropertyChanged {
        
        private string messageField;
        
        /// <remarks/>
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=0)]
        public string message {
            get {
                return this.messageField;
            }
            set {
                this.messageField = value;
                this.RaisePropertyChanged("message");
            }
        }
        
        public event System.ComponentModel.PropertyChangedEventHandler PropertyChanged;
        
        protected void RaisePropertyChanged(string propertyName) {
            System.ComponentModel.PropertyChangedEventHandler propertyChanged = this.PropertyChanged;
            if ((propertyChanged != null)) {
                propertyChanged(this, new System.ComponentModel.PropertyChangedEventArgs(propertyName));
            }
        }
    }
    
    /// <remarks/>
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Xml", "4.7.2612.0")]
    [System.SerializableAttribute()]
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.ComponentModel.DesignerCategoryAttribute("code")]
    [System.Xml.Serialization.XmlTypeAttribute(Namespace="http://manager.ia.zut.locon.pl/")]
    public partial class publisher : object, System.ComponentModel.INotifyPropertyChanged {
        
        private string nameField;
        
        private string urlField;
        
        /// <remarks/>
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=0)]
        public string name {
            get {
                return this.nameField;
            }
            set {
                this.nameField = value;
                this.RaisePropertyChanged("name");
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=1)]
        public string url {
            get {
                return this.urlField;
            }
            set {
                this.urlField = value;
                this.RaisePropertyChanged("url");
            }
        }
        
        public event System.ComponentModel.PropertyChangedEventHandler PropertyChanged;
        
        protected void RaisePropertyChanged(string propertyName) {
            System.ComponentModel.PropertyChangedEventHandler propertyChanged = this.PropertyChanged;
            if ((propertyChanged != null)) {
                propertyChanged(this, new System.ComponentModel.PropertyChangedEventArgs(propertyName));
            }
        }
    }
    
    /// <remarks/>
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Xml", "4.7.2612.0")]
    [System.SerializableAttribute()]
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.ComponentModel.DesignerCategoryAttribute("code")]
    [System.Xml.Serialization.XmlTypeAttribute(Namespace="http://manager.ia.zut.locon.pl/")]
    public partial class book : object, System.ComponentModel.INotifyPropertyChanged {
        
        private string[] authorsField;
        
        private string isbnField;
        
        private int pagesField;
        
        private bool pagesFieldSpecified;
        
        private publisher publisherField;
        
        private string titleField;
        
        private int yearField;
        
        private bool yearFieldSpecified;
        
        private string idField;
        
        /// <remarks/>
        [System.Xml.Serialization.XmlArrayAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=0)]
        [System.Xml.Serialization.XmlArrayItemAttribute("author", Form=System.Xml.Schema.XmlSchemaForm.Unqualified, IsNullable=false)]
        public string[] authors {
            get {
                return this.authorsField;
            }
            set {
                this.authorsField = value;
                this.RaisePropertyChanged("authors");
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=1)]
        public string isbn {
            get {
                return this.isbnField;
            }
            set {
                this.isbnField = value;
                this.RaisePropertyChanged("isbn");
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=2)]
        public int pages {
            get {
                return this.pagesField;
            }
            set {
                this.pagesField = value;
                this.RaisePropertyChanged("pages");
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlIgnoreAttribute()]
        public bool pagesSpecified {
            get {
                return this.pagesFieldSpecified;
            }
            set {
                this.pagesFieldSpecified = value;
                this.RaisePropertyChanged("pagesSpecified");
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=3)]
        public publisher publisher {
            get {
                return this.publisherField;
            }
            set {
                this.publisherField = value;
                this.RaisePropertyChanged("publisher");
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=4)]
        public string title {
            get {
                return this.titleField;
            }
            set {
                this.titleField = value;
                this.RaisePropertyChanged("title");
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=5)]
        public int year {
            get {
                return this.yearField;
            }
            set {
                this.yearField = value;
                this.RaisePropertyChanged("year");
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlIgnoreAttribute()]
        public bool yearSpecified {
            get {
                return this.yearFieldSpecified;
            }
            set {
                this.yearFieldSpecified = value;
                this.RaisePropertyChanged("yearSpecified");
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlAttributeAttribute()]
        public string id {
            get {
                return this.idField;
            }
            set {
                this.idField = value;
                this.RaisePropertyChanged("id");
            }
        }
        
        public event System.ComponentModel.PropertyChangedEventHandler PropertyChanged;
        
        protected void RaisePropertyChanged(string propertyName) {
            System.ComponentModel.PropertyChangedEventHandler propertyChanged = this.PropertyChanged;
            if ((propertyChanged != null)) {
                propertyChanged(this, new System.ComponentModel.PropertyChangedEventArgs(propertyName));
            }
        }
    }
    
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ServiceModel.ServiceContractAttribute(Namespace="http://manager.ia.zut.locon.pl/", ConfigurationName="BookManagerService.BookManager")]
    public interface BookManager {
        
        // CODEGEN: Parametr „book” wymaga dodatkowych informacji o schemacie, których nie można uzyskać w trybie parametru. Określony atrybut to „System.Xml.Serialization.XmlElementAttribute”.
        [System.ServiceModel.OperationContractAttribute(Action="http://manager.ia.zut.locon.pl/BookManager/searchByISBNRequest", ReplyAction="http://manager.ia.zut.locon.pl/BookManager/searchByISBNResponse")]
        [System.ServiceModel.FaultContractAttribute(typeof(BooksClient.BookManagerService.Exception), Action="http://manager.ia.zut.locon.pl/BookManager/searchByISBN/Fault/Exception", Name="Exception")]
        [System.ServiceModel.XmlSerializerFormatAttribute(SupportFaults=true)]
        [return: System.ServiceModel.MessageParameterAttribute(Name="book")]
        BooksClient.BookManagerService.searchByISBNResponse searchByISBN(BooksClient.BookManagerService.searchByISBNRequest request);
        
        [System.ServiceModel.OperationContractAttribute(Action="http://manager.ia.zut.locon.pl/BookManager/searchByISBNRequest", ReplyAction="http://manager.ia.zut.locon.pl/BookManager/searchByISBNResponse")]
        System.Threading.Tasks.Task<BooksClient.BookManagerService.searchByISBNResponse> searchByISBNAsync(BooksClient.BookManagerService.searchByISBNRequest request);
        
        // CODEGEN: Parametr „book” wymaga dodatkowych informacji o schemacie, których nie można uzyskać w trybie parametru. Określony atrybut to „System.Xml.Serialization.XmlElementAttribute”.
        [System.ServiceModel.OperationContractAttribute(Action="http://manager.ia.zut.locon.pl/BookManager/searchByTitleRequest", ReplyAction="http://manager.ia.zut.locon.pl/BookManager/searchByTitleResponse")]
        [System.ServiceModel.FaultContractAttribute(typeof(BooksClient.BookManagerService.Exception), Action="http://manager.ia.zut.locon.pl/BookManager/searchByTitle/Fault/Exception", Name="Exception")]
        [System.ServiceModel.XmlSerializerFormatAttribute(SupportFaults=true)]
        [return: System.ServiceModel.MessageParameterAttribute(Name="book")]
        BooksClient.BookManagerService.searchByTitleResponse searchByTitle(BooksClient.BookManagerService.searchByTitleRequest request);
        
        [System.ServiceModel.OperationContractAttribute(Action="http://manager.ia.zut.locon.pl/BookManager/searchByTitleRequest", ReplyAction="http://manager.ia.zut.locon.pl/BookManager/searchByTitleResponse")]
        System.Threading.Tasks.Task<BooksClient.BookManagerService.searchByTitleResponse> searchByTitleAsync(BooksClient.BookManagerService.searchByTitleRequest request);
        
        // CODEGEN: Parametr „book” wymaga dodatkowych informacji o schemacie, których nie można uzyskać w trybie parametru. Określony atrybut to „System.Xml.Serialization.XmlElementAttribute”.
        [System.ServiceModel.OperationContractAttribute(Action="http://manager.ia.zut.locon.pl/BookManager/searchByAuthorRequest", ReplyAction="http://manager.ia.zut.locon.pl/BookManager/searchByAuthorResponse")]
        [System.ServiceModel.FaultContractAttribute(typeof(BooksClient.BookManagerService.Exception), Action="http://manager.ia.zut.locon.pl/BookManager/searchByAuthor/Fault/Exception", Name="Exception")]
        [System.ServiceModel.XmlSerializerFormatAttribute(SupportFaults=true)]
        [return: System.ServiceModel.MessageParameterAttribute(Name="book")]
        BooksClient.BookManagerService.searchByAuthorResponse searchByAuthor(BooksClient.BookManagerService.searchByAuthorRequest request);
        
        [System.ServiceModel.OperationContractAttribute(Action="http://manager.ia.zut.locon.pl/BookManager/searchByAuthorRequest", ReplyAction="http://manager.ia.zut.locon.pl/BookManager/searchByAuthorResponse")]
        System.Threading.Tasks.Task<BooksClient.BookManagerService.searchByAuthorResponse> searchByAuthorAsync(BooksClient.BookManagerService.searchByAuthorRequest request);
        
        // CODEGEN: Parametr „return” wymaga dodatkowych informacji o schemacie, których nie można uzyskać w trybie parametru. Określony atrybut to „System.Xml.Serialization.XmlElementAttribute”.
        [System.ServiceModel.OperationContractAttribute(Action="http://manager.ia.zut.locon.pl/BookManager/removeBookRequest", ReplyAction="http://manager.ia.zut.locon.pl/BookManager/removeBookResponse")]
        [System.ServiceModel.FaultContractAttribute(typeof(BooksClient.BookManagerService.Exception), Action="http://manager.ia.zut.locon.pl/BookManager/removeBook/Fault/Exception", Name="Exception")]
        [System.ServiceModel.XmlSerializerFormatAttribute(SupportFaults=true)]
        [return: System.ServiceModel.MessageParameterAttribute(Name="return")]
        BooksClient.BookManagerService.removeBookResponse removeBook(BooksClient.BookManagerService.removeBookRequest request);
        
        [System.ServiceModel.OperationContractAttribute(Action="http://manager.ia.zut.locon.pl/BookManager/removeBookRequest", ReplyAction="http://manager.ia.zut.locon.pl/BookManager/removeBookResponse")]
        System.Threading.Tasks.Task<BooksClient.BookManagerService.removeBookResponse> removeBookAsync(BooksClient.BookManagerService.removeBookRequest request);
        
        // CODEGEN: Parametr „book” wymaga dodatkowych informacji o schemacie, których nie można uzyskać w trybie parametru. Określony atrybut to „System.Xml.Serialization.XmlElementAttribute”.
        [System.ServiceModel.OperationContractAttribute(Action="http://manager.ia.zut.locon.pl/BookManager/addBookRequest", ReplyAction="http://manager.ia.zut.locon.pl/BookManager/addBookResponse")]
        [System.ServiceModel.FaultContractAttribute(typeof(BooksClient.BookManagerService.Exception), Action="http://manager.ia.zut.locon.pl/BookManager/addBook/Fault/Exception", Name="Exception")]
        [System.ServiceModel.XmlSerializerFormatAttribute(SupportFaults=true)]
        BooksClient.BookManagerService.addBookResponse addBook(BooksClient.BookManagerService.addBookRequest request);
        
        [System.ServiceModel.OperationContractAttribute(Action="http://manager.ia.zut.locon.pl/BookManager/addBookRequest", ReplyAction="http://manager.ia.zut.locon.pl/BookManager/addBookResponse")]
        System.Threading.Tasks.Task<BooksClient.BookManagerService.addBookResponse> addBookAsync(BooksClient.BookManagerService.addBookRequest request);
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.ServiceModel.MessageContractAttribute(WrapperName="searchByISBN", WrapperNamespace="http://manager.ia.zut.locon.pl/", IsWrapped=true)]
    public partial class searchByISBNRequest {
        
        [System.ServiceModel.MessageBodyMemberAttribute(Namespace="http://manager.ia.zut.locon.pl/", Order=0)]
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified)]
        public string isbn;
        
        public searchByISBNRequest() {
        }
        
        public searchByISBNRequest(string isbn) {
            this.isbn = isbn;
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.ServiceModel.MessageContractAttribute(WrapperName="searchByISBNResponse", WrapperNamespace="http://manager.ia.zut.locon.pl/", IsWrapped=true)]
    public partial class searchByISBNResponse {
        
        [System.ServiceModel.MessageBodyMemberAttribute(Namespace="http://manager.ia.zut.locon.pl/", Order=0)]
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified)]
        public BooksClient.BookManagerService.book book;
        
        public searchByISBNResponse() {
        }
        
        public searchByISBNResponse(BooksClient.BookManagerService.book book) {
            this.book = book;
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.ServiceModel.MessageContractAttribute(WrapperName="searchByTitle", WrapperNamespace="http://manager.ia.zut.locon.pl/", IsWrapped=true)]
    public partial class searchByTitleRequest {
        
        [System.ServiceModel.MessageBodyMemberAttribute(Namespace="http://manager.ia.zut.locon.pl/", Order=0)]
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified)]
        public string title;
        
        public searchByTitleRequest() {
        }
        
        public searchByTitleRequest(string title) {
            this.title = title;
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.ServiceModel.MessageContractAttribute(WrapperName="searchByTitleResponse", WrapperNamespace="http://manager.ia.zut.locon.pl/", IsWrapped=true)]
    public partial class searchByTitleResponse {
        
        [System.ServiceModel.MessageBodyMemberAttribute(Namespace="http://manager.ia.zut.locon.pl/", Order=0)]
        [System.Xml.Serialization.XmlElementAttribute("book", Form=System.Xml.Schema.XmlSchemaForm.Unqualified)]
        public BooksClient.BookManagerService.book[] book;
        
        public searchByTitleResponse() {
        }
        
        public searchByTitleResponse(BooksClient.BookManagerService.book[] book) {
            this.book = book;
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.ServiceModel.MessageContractAttribute(WrapperName="searchByAuthor", WrapperNamespace="http://manager.ia.zut.locon.pl/", IsWrapped=true)]
    public partial class searchByAuthorRequest {
        
        [System.ServiceModel.MessageBodyMemberAttribute(Namespace="http://manager.ia.zut.locon.pl/", Order=0)]
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified)]
        public string author;
        
        public searchByAuthorRequest() {
        }
        
        public searchByAuthorRequest(string author) {
            this.author = author;
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.ServiceModel.MessageContractAttribute(WrapperName="searchByAuthorResponse", WrapperNamespace="http://manager.ia.zut.locon.pl/", IsWrapped=true)]
    public partial class searchByAuthorResponse {
        
        [System.ServiceModel.MessageBodyMemberAttribute(Namespace="http://manager.ia.zut.locon.pl/", Order=0)]
        [System.Xml.Serialization.XmlElementAttribute("book", Form=System.Xml.Schema.XmlSchemaForm.Unqualified)]
        public BooksClient.BookManagerService.book[] book;
        
        public searchByAuthorResponse() {
        }
        
        public searchByAuthorResponse(BooksClient.BookManagerService.book[] book) {
            this.book = book;
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.ServiceModel.MessageContractAttribute(WrapperName="removeBook", WrapperNamespace="http://manager.ia.zut.locon.pl/", IsWrapped=true)]
    public partial class removeBookRequest {
        
        [System.ServiceModel.MessageBodyMemberAttribute(Namespace="http://manager.ia.zut.locon.pl/", Order=0)]
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified)]
        public string id;
        
        public removeBookRequest() {
        }
        
        public removeBookRequest(string id) {
            this.id = id;
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.ServiceModel.MessageContractAttribute(WrapperName="removeBookResponse", WrapperNamespace="http://manager.ia.zut.locon.pl/", IsWrapped=true)]
    public partial class removeBookResponse {
        
        [System.ServiceModel.MessageBodyMemberAttribute(Namespace="http://manager.ia.zut.locon.pl/", Order=0)]
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified)]
        public bool @return;
        
        public removeBookResponse() {
        }
        
        public removeBookResponse(bool @return) {
            this.@return = @return;
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.ServiceModel.MessageContractAttribute(WrapperName="addBook", WrapperNamespace="http://manager.ia.zut.locon.pl/", IsWrapped=true)]
    public partial class addBookRequest {
        
        [System.ServiceModel.MessageBodyMemberAttribute(Namespace="http://manager.ia.zut.locon.pl/", Order=0)]
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified)]
        public BooksClient.BookManagerService.book book;
        
        public addBookRequest() {
        }
        
        public addBookRequest(BooksClient.BookManagerService.book book) {
            this.book = book;
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.ServiceModel.MessageContractAttribute(WrapperName="addBookResponse", WrapperNamespace="http://manager.ia.zut.locon.pl/", IsWrapped=true)]
    public partial class addBookResponse {
        
        public addBookResponse() {
        }
    }
    
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    public interface BookManagerChannel : BooksClient.BookManagerService.BookManager, System.ServiceModel.IClientChannel {
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    public partial class BookManagerClient : System.ServiceModel.ClientBase<BooksClient.BookManagerService.BookManager>, BooksClient.BookManagerService.BookManager {
        
        public BookManagerClient() {
        }
        
        public BookManagerClient(string endpointConfigurationName) : 
                base(endpointConfigurationName) {
        }
        
        public BookManagerClient(string endpointConfigurationName, string remoteAddress) : 
                base(endpointConfigurationName, remoteAddress) {
        }
        
        public BookManagerClient(string endpointConfigurationName, System.ServiceModel.EndpointAddress remoteAddress) : 
                base(endpointConfigurationName, remoteAddress) {
        }
        
        public BookManagerClient(System.ServiceModel.Channels.Binding binding, System.ServiceModel.EndpointAddress remoteAddress) : 
                base(binding, remoteAddress) {
        }
        
        [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
        BooksClient.BookManagerService.searchByISBNResponse BooksClient.BookManagerService.BookManager.searchByISBN(BooksClient.BookManagerService.searchByISBNRequest request) {
            return base.Channel.searchByISBN(request);
        }
        
        public BooksClient.BookManagerService.book searchByISBN(string isbn) {
            BooksClient.BookManagerService.searchByISBNRequest inValue = new BooksClient.BookManagerService.searchByISBNRequest();
            inValue.isbn = isbn;
            BooksClient.BookManagerService.searchByISBNResponse retVal = ((BooksClient.BookManagerService.BookManager)(this)).searchByISBN(inValue);
            return retVal.book;
        }
        
        [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
        System.Threading.Tasks.Task<BooksClient.BookManagerService.searchByISBNResponse> BooksClient.BookManagerService.BookManager.searchByISBNAsync(BooksClient.BookManagerService.searchByISBNRequest request) {
            return base.Channel.searchByISBNAsync(request);
        }
        
        public System.Threading.Tasks.Task<BooksClient.BookManagerService.searchByISBNResponse> searchByISBNAsync(string isbn) {
            BooksClient.BookManagerService.searchByISBNRequest inValue = new BooksClient.BookManagerService.searchByISBNRequest();
            inValue.isbn = isbn;
            return ((BooksClient.BookManagerService.BookManager)(this)).searchByISBNAsync(inValue);
        }
        
        [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
        BooksClient.BookManagerService.searchByTitleResponse BooksClient.BookManagerService.BookManager.searchByTitle(BooksClient.BookManagerService.searchByTitleRequest request) {
            return base.Channel.searchByTitle(request);
        }
        
        public BooksClient.BookManagerService.book[] searchByTitle(string title) {
            BooksClient.BookManagerService.searchByTitleRequest inValue = new BooksClient.BookManagerService.searchByTitleRequest();
            inValue.title = title;
            BooksClient.BookManagerService.searchByTitleResponse retVal = ((BooksClient.BookManagerService.BookManager)(this)).searchByTitle(inValue);
            return retVal.book;
        }
        
        [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
        System.Threading.Tasks.Task<BooksClient.BookManagerService.searchByTitleResponse> BooksClient.BookManagerService.BookManager.searchByTitleAsync(BooksClient.BookManagerService.searchByTitleRequest request) {
            return base.Channel.searchByTitleAsync(request);
        }
        
        public System.Threading.Tasks.Task<BooksClient.BookManagerService.searchByTitleResponse> searchByTitleAsync(string title) {
            BooksClient.BookManagerService.searchByTitleRequest inValue = new BooksClient.BookManagerService.searchByTitleRequest();
            inValue.title = title;
            return ((BooksClient.BookManagerService.BookManager)(this)).searchByTitleAsync(inValue);
        }
        
        [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
        BooksClient.BookManagerService.searchByAuthorResponse BooksClient.BookManagerService.BookManager.searchByAuthor(BooksClient.BookManagerService.searchByAuthorRequest request) {
            return base.Channel.searchByAuthor(request);
        }
        
        public BooksClient.BookManagerService.book[] searchByAuthor(string author) {
            BooksClient.BookManagerService.searchByAuthorRequest inValue = new BooksClient.BookManagerService.searchByAuthorRequest();
            inValue.author = author;
            BooksClient.BookManagerService.searchByAuthorResponse retVal = ((BooksClient.BookManagerService.BookManager)(this)).searchByAuthor(inValue);
            return retVal.book;
        }
        
        [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
        System.Threading.Tasks.Task<BooksClient.BookManagerService.searchByAuthorResponse> BooksClient.BookManagerService.BookManager.searchByAuthorAsync(BooksClient.BookManagerService.searchByAuthorRequest request) {
            return base.Channel.searchByAuthorAsync(request);
        }
        
        public System.Threading.Tasks.Task<BooksClient.BookManagerService.searchByAuthorResponse> searchByAuthorAsync(string author) {
            BooksClient.BookManagerService.searchByAuthorRequest inValue = new BooksClient.BookManagerService.searchByAuthorRequest();
            inValue.author = author;
            return ((BooksClient.BookManagerService.BookManager)(this)).searchByAuthorAsync(inValue);
        }
        
        [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
        BooksClient.BookManagerService.removeBookResponse BooksClient.BookManagerService.BookManager.removeBook(BooksClient.BookManagerService.removeBookRequest request) {
            return base.Channel.removeBook(request);
        }
        
        public bool removeBook(string id) {
            BooksClient.BookManagerService.removeBookRequest inValue = new BooksClient.BookManagerService.removeBookRequest();
            inValue.id = id;
            BooksClient.BookManagerService.removeBookResponse retVal = ((BooksClient.BookManagerService.BookManager)(this)).removeBook(inValue);
            return retVal.@return;
        }
        
        [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
        System.Threading.Tasks.Task<BooksClient.BookManagerService.removeBookResponse> BooksClient.BookManagerService.BookManager.removeBookAsync(BooksClient.BookManagerService.removeBookRequest request) {
            return base.Channel.removeBookAsync(request);
        }
        
        public System.Threading.Tasks.Task<BooksClient.BookManagerService.removeBookResponse> removeBookAsync(string id) {
            BooksClient.BookManagerService.removeBookRequest inValue = new BooksClient.BookManagerService.removeBookRequest();
            inValue.id = id;
            return ((BooksClient.BookManagerService.BookManager)(this)).removeBookAsync(inValue);
        }
        
        [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
        BooksClient.BookManagerService.addBookResponse BooksClient.BookManagerService.BookManager.addBook(BooksClient.BookManagerService.addBookRequest request) {
            return base.Channel.addBook(request);
        }
        
        public void addBook(BooksClient.BookManagerService.book book) {
            BooksClient.BookManagerService.addBookRequest inValue = new BooksClient.BookManagerService.addBookRequest();
            inValue.book = book;
            BooksClient.BookManagerService.addBookResponse retVal = ((BooksClient.BookManagerService.BookManager)(this)).addBook(inValue);
        }
        
        [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
        System.Threading.Tasks.Task<BooksClient.BookManagerService.addBookResponse> BooksClient.BookManagerService.BookManager.addBookAsync(BooksClient.BookManagerService.addBookRequest request) {
            return base.Channel.addBookAsync(request);
        }
        
        public System.Threading.Tasks.Task<BooksClient.BookManagerService.addBookResponse> addBookAsync(BooksClient.BookManagerService.book book) {
            BooksClient.BookManagerService.addBookRequest inValue = new BooksClient.BookManagerService.addBookRequest();
            inValue.book = book;
            return ((BooksClient.BookManagerService.BookManager)(this)).addBookAsync(inValue);
        }
    }
}
