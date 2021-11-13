# Introduction
This is the artifact of the paper "[How Much Support Can API Recommendation Methods Provide for Component-based Synthesis?](https://ieeexplore.ieee.org/document/9202852)".

This work is based on the state-of-art synthesis tool named [SyPet](https://github.com/utopia-group/sypet), which is proposed by Yu Feng et al. in the paper "[Component-Based Synthesis for Complex APIs](https://dl.acm.org/doi/10.1145/3009837.3009851)". 

## Usage
#Provide the task description

You should modify the file `new_keywords`, and replace it with your task description.

#Search and Extract useful API methods for your task.
```
$ python ExtractWebpage.py
```
After obtaining the list of API methods, copying them to the directory of the synthesis tool.
```
$ cp content_results/ ../mysypet/content_results
```

#Synthesis the task
```
$ ant
$ ./run-sypet.sh benchmarks/google/your-task-ID/benchmarkID.json
```

#Requirements
  - JDK 1.7+
  - ANT
  - Ubuntu/macOS (when synthesis)
  - Python 2.7
 
 For more information, please refer to our paper. 
